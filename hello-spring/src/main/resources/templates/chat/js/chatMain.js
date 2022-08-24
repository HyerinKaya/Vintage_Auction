var enterForm = document.querySelector('#enterForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var currentUser = document.getElementsByTagName("span")[0].innerHTML;
var receiver = document.getElementsByTagName("span")[1].innerHTML;

var stompClient = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];



function connect(event) {
    event.preventDefault();

    console.log("connected");
    console.log("보내는 이(현재 유저):", currentUser);
    console.log("받는 이:", receiver);

    if(currentUser) {

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}


function onConnected() {
    // user 개인 구독
    stompClient.subscribe('/user/'+ currentUser +'/queue/messages', onMessageReceived);


    //connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    event.preventDefault();
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: currentUser,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send('/user/'+ receiver +'/queue/messages', {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }

    /*
    내가 쓴 메시지 표시
     */
    var message = chatMessage;

    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');

    //뷰에 유저 아이콘 표시하기
    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    var currentUserElement = document.createElement('span');
    var currentUserText = document.createTextNode(message.sender);
    currentUserElement.appendChild(currentUserText);
    messageElement.appendChild(currentUserElement);

    //뷰에 채팅 내용 표시하기
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function onMessageReceived(payload) {
    //구독한 destination으로 수신한 메시지 파싱
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');


    messageElement.classList.add('chat-message');

    //뷰에 유저 아이콘 표시하기
    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    var currentUserElement = document.createElement('span');
    var currentUserText = document.createTextNode(message.sender);
    currentUserElement.appendChild(currentUserText);
    messageElement.appendChild(currentUserElement);

    //뷰에 채팅 내용 표시하기
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

enterForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);