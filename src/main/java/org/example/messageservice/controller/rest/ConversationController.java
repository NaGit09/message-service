package org.example.messageservice.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.messageservice.controller.api.IConversationController;
import org.example.messageservice.models.dto.ApiResponse;
import org.example.messageservice.models.dto.ConversationRecord.CreateConversation;
import org.example.messageservice.models.entity.Conversation;
import org.example.messageservice.service.imp.ConversationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController implements IConversationController {

    private final ConversationService conversationService;

    @Override
    public ResponseEntity<?> getConversations(UUID userId) {

        List<Conversation> conversations = conversationService.getConversations(userId);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Get conversations successfully !")
                .data(conversations)
                .build());
    }

    @Override
    public ResponseEntity<?> createConversation(@Valid CreateConversation conv) {

        Conversation conversations = conversationService.createConversation(conv);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Create conversation successfully !")
                .data(conversations)
                .build());
    }

    @Override
    public ResponseEntity<?> acceptConversation(String convId) {
        Boolean accepted = conversationService.acceptConversation(convId);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Accept conversation successfully !")
                .data(accepted)
                .build());
    }


    @Override
    public ResponseEntity<?> requestConversation(UUID userId) {
        List<Conversation> conversations = conversationService.getConversationRequest(userId);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Request conversation successfully !")
                .data(conversations)

                .build());
    }

    @Override
    public ResponseEntity<?> deleteConversation(String convId) {
        boolean deleted = conversationService.deleteConversation(convId);

        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Delete conversation successfully !")
                .data(deleted)
                .build());
    }


}
