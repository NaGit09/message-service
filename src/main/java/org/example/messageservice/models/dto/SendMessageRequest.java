package org.example.messageservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.messageservice.models.entity.MediaAttachment;

import java.util.UUID;

@Data
public class SendMessageRequest {
    @NotBlank
    private String conversationId;
    @NotBlank
    private UUID senderId;
    private String content;
    private MediaAttachment media;
}
