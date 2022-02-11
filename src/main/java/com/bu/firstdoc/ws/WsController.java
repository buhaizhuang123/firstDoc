package com.bu.firstdoc.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 3:26 下午 2022/2/10
 * @mark WsController
 */
@RequestMapping("ws")
@RestController
public class WsController {

    @GetMapping("{session}/send")
    public void send(@PathVariable("session") String session, String msg){
        TextMessage textMessage = new TextMessage(session);
        try {
            SessionManager.get(session).sendMessage(textMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
