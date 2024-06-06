/* eslint-disable react/prop-types */
import React, { useEffect, useState } from "react";
import "./Home.css";
import data from "src/data.json";
import PatientHeader from "src/components/PatientHeader/PatientHeader";
import PatientFooter from "src/components/PatientFooter/PatientFooter";

import { BsQuestionCircleFill } from "react-icons/bs";
import { TbMicrophone } from "react-icons/tb";
import PatientHistory from "src/components/PatientHistory/PatientHistory";

import { useNavigate } from "react-router-dom";
import { getSettings } from "../../../lib/api";
import { useRedirectToLogin } from "src/hooks/useSession";

import Logo from "src/assets/logo.svg";
import { Button, Modal, Card, Stack, Placeholder } from "react-bootstrap";


export default function Home({ session }) {
	useRedirectToLogin(session, "/patient/login");
	const navigate = useNavigate();
	const [settings, setSettings] = useState([]);
	const navigateToContactsFromQuestion = () => {
		navigate("/patient/recording");
		localStorage.setItem("isQuestion", "true");
	};

	const navigateToContactsFromRequest = () => {
		navigate("/patient/recording");
		localStorage.setItem("isQuestion", "false");
	};

	const patient = data.patient;

	const navigateToASL4ALL = () => {
		navigate("/patient/ASL4ALL");
	};

	const fetchSettings = async () => {
		if (!session.user) return;
		try {
			const settingsData = await getSettings(session);
			setSettings(settingsData);
		} catch (error) {
			console.error("Error fetching data:", error);
		}
	};

	const [show, setShow] = useState(false);

	const handleClose = () => setShow(false);
	const handleShow = () => setShow(true);

	useEffect(() => {
		fetchSettings();
	}, [session]);

	return (
		<div className="patient__home">
			<PatientHeader session={session}/>
			<main>
				<div className="patient__home container">
					<div className="menu">
						<h1>{settings.hospital_title}</h1>
						<h2>{settings.hospital_description} </h2>
					</div>


					<button className="bg-red-600 button-left" onClick={navigateToASL4ALL}>
        Switch to ASL
					</button>

					<Button variant="primary" onClick={handleShow}>
						Food Menu
					</Button>

					<Modal
						show={show}
						onHide={handleClose}
						backdrop="static"
						keyboard={false}
						size="lg"
					>
						<Modal.Header closeButton>
							<Modal.Title>Food Menu</Modal.Title>
						</Modal.Header>
						<Modal.Body>
							<Stack direction="horizontal" gap={3}>
								<Card style={{ width: "18rem" }}>
									<Card.Img variant="top" src={Logo} />
									<Card.Body>
										<Card.Title>Breakfast</Card.Title>
										<Card.Text>
											<Placeholder as={Card.Text} animation="glow">
												<Placeholder xs={7} /> <Placeholder xs={4} /> <Placeholder xs={4} />{" "}
												<Placeholder xs={6} /> <Placeholder xs={8} />
											</Placeholder>
										</Card.Text>
									</Card.Body>
								</Card>
								<Card style={{ width: "18rem" }}>
									<Card.Img variant="top" src={Logo} />
									<Card.Body>
										<Card.Title>Lunch</Card.Title>
										<Card.Text>
											<Placeholder as={Card.Text} animation="glow">
												<Placeholder xs={7} /> <Placeholder xs={4} /> <Placeholder xs={4} />{" "}
												<Placeholder xs={6} /> <Placeholder xs={8} />
											</Placeholder>
										</Card.Text>
									</Card.Body>
								</Card>
								<Card style={{ width: "18rem" }}>
									<Card.Img variant="top" src={Logo} />
									<Card.Body>
										<Card.Title>Dinner</Card.Title>
										<Card.Text>
											<Placeholder as={Card.Text} animation="glow">
												<Placeholder xs={7} /> <Placeholder xs={4} /> <Placeholder xs={4} />{" "}
												<Placeholder xs={6} /> <Placeholder xs={8} />
											</Placeholder>
										</Card.Text>
									</Card.Body>
								</Card>
							</Stack>
						</Modal.Body>
					</Modal>


					<div className="rq-container">
						<div className="question-container">
							<h1 className="title">{patient.questionTitle}</h1>
							<button onClick={navigateToContactsFromQuestion}>
								<BsQuestionCircleFill size={260} className="icon" />
								<h1>{patient.questionSubtitle}</h1>
								<h3>{patient.confirmation}</h3>
							</button>
						</div>

						<div className="separator"></div>

						<div className="request-container">
							<h1 className="title">{patient.requestTitle}</h1>
							<button onClick={navigateToContactsFromRequest}>
								<TbMicrophone size={260} className="icon" />
								<h1>{patient.requestSubtitle}</h1>
								<h3>{patient.confirmation}</h3>
							</button>
						</div>
					</div>
				</div>
			</main>
			<PatientHistory session={session}/>
			<PatientFooter session={session}/>
		</div>
	);
}
