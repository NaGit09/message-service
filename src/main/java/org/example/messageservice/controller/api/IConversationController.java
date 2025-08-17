package org.example.messageservice.controller.api;


import org.example.messageservice.models.dto.ConversationRecord.CreateConversation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


public interface IConversationController {

    @GetMapping("/{userId}")
    ResponseEntity<?> getConversations(@PathVariable UUID userId);

    @PostMapping("/create")
    ResponseEntity<?> createConversation(@RequestBody CreateConversation conv);

    @PatchMapping("/accept/{convId}")
    ResponseEntity<?> acceptConversation(@PathVariable String convId);

    @GetMapping("/request/{userId}")
    ResponseEntity<?> requestConversation(@PathVariable UUID userId);

    @DeleteMapping("/delete/{convId}")
    ResponseEntity<?> deleteConversation(@PathVariable String convId);

}
