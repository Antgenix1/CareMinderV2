import React, { useState, useEffect } from "react";
import {
  BsQuestionCircleFill,
  BsArrowDownRightCircleFill,
  BsSend,
} from "react-icons/bs";
import moment from "moment";
import { createChatMessage, getChatMessages, getRequests } from "src/lib/api";

export default function PatientHistoryRequest({ request }) {
  const [isOpen, setIsOpen] = useState(false);
  const [messageText, setMessageText] = useState();
  const [chat, setChat] = useState([]);

  useEffect(() => {
    fetchChatMessages();
  }, []);

  async function fetchChatMessages() {
    try {
      const resp = await getChatMessages(request.id);
      setChat(resp);
    } catch (error) {
      console.error("Error fetching chat messages");
    }
  }

  async function newMessage() {
    try {
      const resp = await createChatMessage(request.id, {
        text: messageText,
        from_patient: true,
      });
      fetchChatMessages();
      setMessageText("");
    } catch (error) {
      console.error("Error creating chat messages");
    }
  }

  const getStateText = (state) => {
    switch (state) {
      case 0:
        return "Waiting";
      case 1:
        return "Processing";
      case 2:
        return "Finished";
      default:
        return "Unknown State Text";
    }
  };

  const timeAgo = (timestamp) => {
    return moment(timestamp).fromNow();
  };

  return (
    <div
      key={request.id}
      className={`request-item ${getStateText(request.state)}`}
    >
      <div
        className={`top-container ${getStateText(request.state)}`}
        onClick={() => setIsOpen(!isOpen)}
      >
        <div className="icon-container">
          {request.is_question ? (
            <BsQuestionCircleFill size={50} className="icon" />
          ) : (
            <BsArrowDownRightCircleFill size={50} className="icon" />
          )}
        </div>
        <div className="content-container">
          <div className="info-container">
            <h2>{getStateText(request.state)}</h2>
            <p className="time">{timeAgo(request.time)}</p>
          </div>
          <div className="text-container">
            <p>{request.text}</p>
          </div>
        </div>
      </div>
      <div
        className={`chat-container ${getStateText(request.state)} ${
          isOpen ? "open" : "closed"
        }`}
      >
        <div className="chat-container__messages">
          {chat &&
            chat.map((chatMessage) => {
              return (
                <div
                  key={request.id + "-" + chatMessage.id}
                  className={`chat-item patient-${chatMessage.from_patient}`}
                >
                  <p>{chatMessage.text}</p>
                  <p className="chat-time">{timeAgo(chatMessage.time)}</p>
                </div>
              );
            })}
        </div>
        <div className="chat-container__input">
          <input
            type="text"
            placeholder="Type a message..."
            onChange={(e) => setMessageText(e.target.value)}
            value={messageText}
          />
          <button onClick={newMessage}>
            <BsSend size={20} />
          </button>
        </div>
      </div>
    </div>
  );
}