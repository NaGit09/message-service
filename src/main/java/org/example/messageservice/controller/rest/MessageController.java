package org.example.messageservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.controller.api.IMessageController;
import org.example.messageservice.models.dto.SendMessageRequest;
import org.example.messageservice.models.entity.Messages;
import org.example.messageservice.service.imp.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController implements IMessageController {
    private final MessageService messageService;


    @Override
    public ResponseEntity<Messages> send(SendMessageRequest req) {
        Messages msg = Messages.builder()
                .conversationId(req.getConversationId())
                .senderId(req.getSenderId())
                .content(req.getContent())
                .attachment(req.getMedia())
                .status("sent")
                .createdAt(Instant.now())
                .build();
        Messages sent = messageService.sendMessage(msg);
        return ResponseEntity.ok(sent);
    }
}
