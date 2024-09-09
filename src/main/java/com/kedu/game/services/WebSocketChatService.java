package com.kedu.game.services;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;

import java.util.*;

@Component
public class WebSocketChatService implements WebSocketHandler {
    private static Set<WebSocketSession> clients = Collections.synchronizedSet(new HashSet<>());
    private static Logger log = LoggerFactory.getLogger(WebSocketChatService.class);

    // 소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("session ID:::: "+session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message instanceof TextMessage){
            String messages = ((TextMessage) message).getPayload();
            log.info(messages);
            session.sendMessage(new TextMessage(messages));
        }else{
            log.info("received non-text message");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
