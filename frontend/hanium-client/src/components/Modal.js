import React, { useEffect, useRef, useState } from "react";
import "../css/Modal.css";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import axios from "axios";

function Modal({ chatObj, setOpenModal }) {
  let stompClient = useRef({});
  const [message, setMessage] = useState("");
  const [chatMessages, setChatMessages] = useState([]);
  const scrollRef = useRef();

  useEffect(() => {
    scrollToBottom();
    connect();
    enterChatRoom();
    
    //return () => disconnect()
  }, []);

  const scrollToBottom = () => {
    //this.scrollRef.scrollIntoView({ behavior: "smooth" });
    scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
  }

  const scroll = (event) => {
  }

  const connect = (event) => {
  
    if(1) {//'ws://localhost:8080/ws'
      var socket = new SockJS('http://localhost:8080/ws');
      stompClient.current = Stomp.over(socket);
      stompClient.current.connect({}, () => {
        setTimeout(function() {
          onConnected()
        }, 500)
      })
    }
}

function onConnected() {
  // user 개인 구독
  stompClient.current.subscribe('/user/' + chatObj.sellerNo.memberNo + '/queue/messages', onMessageReceived);
}



const sendMessage = (event) => {
    
  //event.preventDefault();
  var messageContent = message.trim();
  if(messageContent && stompClient) {
      var chatMessage = {
          sender: chatObj.sellerNo.memberNo,
          receiver: chatObj.buyerNo.memberNo,
          content: message,
          chatroom: chatObj.id,
          type: 'CHAT'
      };
      
      console.log(chatMessage)
      stompClient.current.send('/room/'+chatObj.id+'/queue/messages', {}, JSON.stringify(chatMessage)); //json 직렬화해서 보내기
      stompClient.current.send('/app/chat', {}, JSON.stringify({'content': message,'senderId':chatObj.buyerNo.memberNo,
          'receiverId': chatObj.sellerNo.memberNo, 'chatroomId': chatObj.id}));

      setMessage("");
  }


}


function onMessageReceived(payload) {
  //구독한 destination으로 수신한 메시지 파싱
  var newMessage = JSON.parse(payload.body);
  console.log(newMessage);
  setChatMessages((chatMessages) => {
    return [...chatMessages, newMessage];
  })
  console.log(chatMessages);
}



  const enterChatRoom =() => {
    if (chatObj) {
      axios.get(`/api/chat/${chatObj.buyerNo.memberId}/${chatObj.id}`)
      .then(response => {
          console.log(response.data)
          setChatMessages(response.data);
      })
    } else {
      alert("로그인이 필요합니다.")
    }
  }
  
  const onChange = (event) => {
    setMessage(event.target.value);
  }

  const publish = (event) => {
    event.preventDefault();
    if (!stompClient.current.connected || !message) 
      return;

    sendMessage();
  };
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <div className="titleCloseBtn">
          <button
            onClick={() => {
              setOpenModal(false);
            }}
          >
            X
          </button>
        </div>
        <div className="title">

        </div>
        <div className="body" onScroll={scroll}
        ref={scrollRef}>
          <ul className="messageList">
          {chatMessages &&
          chatMessages.map((m)=>{
            if (m.senderId == chatObj.buyerNo.memberNo)
              return <li className="chat me">{m.content}</li>
            else
              return <li className="chat other">{m.content}</li>
          })}
          </ul>
        </div>
        <form className="footer">
          <input
            name="text"
            placeholder="메시지를 입력하세요."
            required
            className="message-input"
            id="message-input"
            value={message}
            onChange={onChange}
          />
          <button onClick={publish}>전송</button>
        </form>
      </div>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    </div>
  );
}

export default Modal;
