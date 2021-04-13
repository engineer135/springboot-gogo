package com.springboot.gogo.first.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/myHandler")
public class MyHandler extends TextWebSocketHandler {
    // 세션 리스트
    private List<WebSocketSession> sessionList = new ArrayList();

    // 클라이언트 연결시 실행
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("연결됨"+ session.getId());
    }

    //클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(session.getId() + "로 부터 받음" + message.getPayload());
        //모든 유저에게 메세지 출력
        for(WebSocketSession sess : sessionList) {
            sess.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    //클라이언트 연결을 끊었을 때 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println("연결 끊김."+ session.getId());
    }
}
