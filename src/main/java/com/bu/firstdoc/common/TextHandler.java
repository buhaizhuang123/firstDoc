package com.bu.firstdoc.common;

import com.bu.firstdoc.ws.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author haizhuangbu
 * @date 3:19 下午 2022/2/10
 * @mark TextHandler
 */
@Slf4j
public class TextHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (session.isOpen()) {
            log.info("receive websocket msg :{},session : {}",message,session.getId());
            TextMessage textMessage = new TextMessage(message.getPayload() + "receive at server");
            session.sendMessage(textMessage);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionManager.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        SessionManager.remove(session.getId());
    }
}
