const chatInput = document.querySelector('#chatInput');

let sock = new SockJS("http://localhost:8080/myHandler/");
        sock.onmessage = onMessage;
        sock.onclose = onClose;

document.getElementById('sendMessage').addEventListener('click', (event) => {
    console.log(chatInput.value);
    sendMessage();
});

// 메시지 전송
	function sendMessage() {
		sock.send(document.getElementById('chatInput').value);
	}
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		document.getElementById('messageArea').innerHTML += data + "<br/>";
	}
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		document.getElementById('messageArea').innerHTML += "연결종료<br/>";

	}