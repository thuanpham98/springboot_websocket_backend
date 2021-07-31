package com.thuannek.config;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.Decoder.Binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springframework.http.HttpHeaders;
import java.net.InetSocketAddress;

// test 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;


public class MyBinaryWebSocketHandler extends BinaryWebSocketHandler{
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(MyBinaryWebSocketHandler.class);


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        InetSocketAddress clientAddress = session.getRemoteAddress();

        //the messages will be broadcasted to all users.
        logger.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());
        sessions.add(session);
        ControllerSession.setSessions(session);
        super.afterConnectionEstablished(session);
        for(WebSocketSession webSocketSession : sessions) {
			webSocketSession.sendMessage(new TextMessage("Hello " + webSocketSession.getRemoteAddress().getHostString()));
		}
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Connection closed by {}:{}", session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
        sessions.remove(session);
        ControllerSession.removeSessions(session);
        super.afterConnectionClosed(session, status);
    }
 
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage  message) throws Exception {
        super.handleBinaryMessage(session, message);
        System.out.println(ControllerSession.getSessions().size());
        ControllerSession.getSessions().forEach(webSocketSession -> {
            try {
                System.out.println(message.getPayload().get(4));
                webSocketSession.sendMessage(message);
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }
}
