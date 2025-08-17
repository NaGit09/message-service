package org.example.messageservice.controller.api;

import org.example.messageservice.models.entity.Messages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public interface IChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/conversations")
    Messages sendMessage(@Payload Messages message) ;

    @MessageMapping("/chat.edit")
    @SendTo("/topic/conversations")
    Messages editMessage(@Payload Messages message) ;

    @MessageMapping("/chat.recall")
    @SendTo("/topic/conversations")
    Messages recallMessage(@Payload Messages message) ;

    @MessageMapping("/chat.react")
    @SendTo("/topic/conversations")
    Messages reactMessage(@Payload Messages message) ;


}
