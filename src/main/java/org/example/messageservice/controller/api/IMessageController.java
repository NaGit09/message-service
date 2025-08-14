package org.example.messageservice.controller.api;

import jakarta.validation.Valid;
import org.example.messageservice.models.dto.SendMessageRequest;
import org.example.messageservice.models.entity.Conversation;
import org.example.messageservice.models.entity.Messages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IMessageController {
    @PostMapping("/conversations")
    ResponseEntity<Conversation> createConversation(@RequestBody Conversation conv);

    @GetMapping("/conversations/{id}/messages")
    ResponseEntity<List<Messages>> getMessages(@PathVariable String id);

    @PostMapping("/messages/send")
    ResponseEntity<Messages> send(@Valid @RequestBody SendMessageRequest req);
}
