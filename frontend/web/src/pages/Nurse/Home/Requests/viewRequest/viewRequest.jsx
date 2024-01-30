import React, { useState, useEffect, useRef } from "react";
import Filter from "src/components/Filter/Filter";
import { BiLoaderCircle } from "react-icons/bi";
import { FaCheckCircle } from "react-icons/fa";
import { MdOutlineDownloading } from "react-icons/md";
import styles from "./viewRequest.module.css";
import flw from "../Requests.module.css";
import Request from "src/components/Request/Request";
import { getAreas, getRequestsFiltered, updateRequest } from "src/lib/api";
import { useRedirectToLogin } from "src/hooks/useSession";
import data from "../../../../../data.json";

export default function ViewRequest({ session }) {
  useRedirectToLogin(session, "nurse/login");
  const nurse = data.nurse;

  const [selectedOptions, setSelectedOptions] = useState({
    waiting: {
      job: {},
      area: {},
    },
    ongoing: {
      job: {},
      area: {},
    },
  });

  const [waiting, setWaiting] = useState([]);
  const [ongoing, setOngoing] = useState([]);

  const [filterOptions, setFilterOptions] = useState({
    job: [
      { value: "request", description: "Request" },
      { value: "question", description: "Question" },
    ],
    patient: [],
    area: [],
  });

  const [selItem, setSelItem] = useState({
    i: null,
    s: null,
    item: { isQuestion: false, text: "", date: new Date() },
  });

  const [holding, setHolding] = useState(false);

  const pressTimer = useRef(null);

  async function fetchFilterOptions() {
    try {
      const resp = await getAreas(session);
      const areas = resp.map((area) => ({
        value: area.id,
        description: area.name,
      }));
      setFilterOptions((prev) => {
        return {
          ...prev,
          area: areas,
        };
      });
    } catch (error) {}
  }

  useEffect(() => {
    fetchFilterOptions();
  }, []);

  const handleCheckBoxChange = (event, filterName, section) => {
    const { name, checked } = event.target;
    setSelectedOptions((prevSelectedOptions) => {
      return {
        ...prevSelectedOptions,
        [section]: {
          ...prevSelectedOptions[section],
          [filterName]: {
            ...prevSelectedOptions[section][filterName],
            [name]: checked,
          },
        },
      };
    });
  };

  const handleWaitingCheckBoxChange = (event, filterName) => {
    handleCheckBoxChange(event, filterName, "waiting");
  };

  const handleOngoingCheckBoxChange = (event, filterName) => {
    handleCheckBoxChange(event, filterName, "ongoing");
  };

  const handleMouseDown = (i, s, item) => {
    pressTimer.current = setTimeout(() => {
      setHolding(true);
      setSelItem({ i, s, item });
    }, 500);
  };

  const handleMouseMove = (e) => {
    const item = document.querySelector(`.${styles.follow}`);

    if (item) {
      item.style.setProperty("--x", `${e.clientX}px`);
      item.style.setProperty("--y", `${e.clientY}px`);
    }
  };

  const handleMouseUp = async (e) => {
    clearTimeout(pressTimer.current);
    if (!holding) return false;

    setHolding(false);
    setSelItem({
      i: null,
      s: null,
      item: { isQuestion: false, text: "", date: new Date() },
    });
    const targetElement = e.target.getAttribute("name");

    if (!targetElement) return false;

    if (targetElement === "finishArea") {
      const item = selItem.s === "r" ? ongoing[selItem.i] : waiting[selItem.i];
      await handleStateChangeDelete(item.id);

      selItem.s === "l"
        ? setWaiting(waiting.filter((_, i) => i !== selItem.i))
        : setOngoing(ongoing.filter((_, i) => i !== selItem.i));
    } else if (targetElement.charAt(0) !== selItem.s) {
      const item = selItem.s === "r" ? ongoing[selItem.i] : waiting[selItem.i];
      if (selItem.s === "l") {
        await handleStateChangeMine(item.id);
        item.state = 1;
        setWaiting(waiting.filter((_, i) => i !== selItem.i));
        setOngoing([...ongoing, item]);
      } else {
        await handleStateChangeGlobal(item.id);
        item.state = 0;
        setOngoing(ongoing.filter((_, i) => i !== selItem.i));
        setWaiting([...waiting, item]);
      }
    }
  };

  async function handleStateChangeMine(id) {
    try {
      const getAllRequests = await updateRequest(
        session,
        id,
        1,
        session.user.id
      );
    } catch (error) {
      console.error(error);
    }
  }

  async function handleStateChangeGlobal(id) {
    try {
      const getAllRequests = await updateRequest(session, id, 0, null);
    } catch (error) {
      console.error(error);
    }
  }

  async function handleStateChangeDelete(id) {
    try {
      const getAllRequests = await updateRequest(session, id, 2, null);
    } catch (error) {
      console.error(error);
    }
  }

  async function fetchRequests(filters, areas) {
    let getAllRequests = [];
    for (const [areaKey, areaValue] of Object.entries(areas)) {
      if (!areaValue) continue;
      const resp = await getRequestsFiltered(session, {
        ...filters,
        tabletArea: areaKey,
      });
      getAllRequests.push(...resp);
    }
    return getAllRequests;
  }

  async function fetchWaitingRequests() {
    try {
      const getAllRequests = await fetchRequests(
        { staff: null, state: 0 },
        selectedOptions.waiting.area
      );
      setWaiting(getAllRequests);
    } catch (error) {
      console.error(error);
    }
  }

  async function fetchOngoingRequests() {
    try {
      const getAllRequests = await fetchRequests(
        {
          staff: session.user.id,
          state: 1,
        },
        selectedOptions.ongoing.area
      );
      setOngoing(getAllRequests);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    window.addEventListener("mousemove", handleMouseMove);
    window.addEventListener("mouseup", handleMouseUp);

    return () => {
      window.removeEventListener("mousemove", handleMouseMove);
      window.removeEventListener("mouseup", handleMouseUp);
    };
  }, [holding]);

  useEffect(() => {
    fetchWaitingRequests();
    fetchOngoingRequests();

    const intervalId = setInterval(() => {
      fetchWaitingRequests();
      fetchOngoingRequests();
    }, 5000);

    return () => clearInterval(intervalId);
  }, [selectedOptions]);

  return (
    <>
      <div
        className={`${styles.follow} ${flw.follow} ${
          holding ? "" : styles.hide
        }`}
      >
        <Request
          request={selItem.item}
          session={session}
          from_patient={false}
        />
      </div>
      <div className={styles.wrapper}>
        <div>
          <div className={styles.title}>
            <BiLoaderCircle />
            <h2>{nurse.nurseHomeGlobalRequestsHeader}</h2>
          </div>
          <div className={styles.filter}>
            <Filter
              title={nurse.filterByJob}
              options={filterOptions.job}
              selectedOptions={selectedOptions["waiting"]["job"]}
              handleCheckboxChange={(e) =>
                handleWaitingCheckBoxChange(e, "job")
              }
            />
            <Filter
              title={nurse.filterByArea}
              options={filterOptions.area}
              selectedOptions={selectedOptions["waiting"]["area"]}
              handleCheckboxChange={(e) =>
                handleWaitingCheckBoxChange(e, "area")
              }
            />
          </div>
          {holding ? <div className={styles.area} name="leftArea"></div> : ""}
          <div
            className={styles.waiting}
            style={holding ? { transform: "translateY(-100%)" } : {}}
          >
            {waiting.map((item, i) => (
              <div
                key={i}
                onMouseDown={(e) => handleMouseDown(i, "l", item)}
                className={
                  selItem.i === i && selItem.s === "l" ? styles.hide : ""
                }
              >
                <Request
                  request={item}
                  session={session}
                  from_patient={false}
                />
              </div>
            ))}
          </div>
        </div>
        <span className={styles.line}></span>
        <div className={styles.right}>
          <div className={styles.title}>
            <MdOutlineDownloading />
            <h2>{nurse.nurseHomeMyRequestHeader}</h2>
          </div>
          <div className={styles.filter}>
            <Filter
              title={nurse.filterByJob}
              options={filterOptions.job}
              selectedOptions={selectedOptions["ongoing"]["job"]}
              handleCheckboxChange={(e) =>
                handleOngoingCheckBoxChange(e, "job")
              }
            />
            <Filter
              title={nurse.filterByArea}
              options={filterOptions.area}
              selectedOptions={selectedOptions["ongoing"]["area"]}
              handleCheckboxChange={(e) =>
                handleOngoingCheckBoxChange(e, "area")
              }
            />
          </div>
          {holding ? <div className={styles.area} name="rightArea"></div> : ""}
          <div
            className={styles.ongoing}
            style={holding ? { transform: "translateY(-100%)" } : {}}
          >
            {ongoing.map((item, i) => (
              <div
                key={i}
                onMouseDown={(e) => handleMouseDown(i, "r", item)}
                className={
                  selItem.i === i && selItem.s === "r" ? styles.hide : ""
                }
              >
                <Request
                  request={item}
                  session={session}
                  from_patient={false}
                />
              </div>
            ))}
          </div>
          {holding ? (
            <div className={styles.finishArea} name="rightArea"></div>
          ) : (
            ""
          )}
          <div className={styles.finishArea} name="finishArea"></div>
          <div className={styles.finishButton}>
            <FaCheckCircle size={90} className={styles.finishCheck} />
          </div>
        </div>
      </div>
    </>
  );
}
