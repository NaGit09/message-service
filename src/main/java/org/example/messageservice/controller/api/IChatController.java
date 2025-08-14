package org.example.messageservice.controller.api;

import org.example.messageservice.models.entity.Messages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public interface IChatController {
    @MessageMapping("/chat.send")
    @SendTo("/topic/conversations")
    // fallback, actual messaging uses SimpMessagingTemplate with conversation-specific topic
    Messages sendMessage(@Payload Messages message) ;
}
