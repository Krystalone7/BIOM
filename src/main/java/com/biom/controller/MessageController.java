package com.biom.controller;

import com.biom.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private  SimpMessagingTemplate template;

    @MessageMapping("/sendToUser")
    public void sendToUser(@Payload ChatMessage chatMessage){

        String authedSender = chatMessage.getSender();
        chatMessage.setSender(authedSender);
        String recipient = chatMessage.getRecipient();
        template.convertAndSendToUser(authedSender, "/queue/messages", chatMessage);
        template.convertAndSendToUser(recipient, "/queue/messages", chatMessage);
    }
}
