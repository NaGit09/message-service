package org.example.messageservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
@Builder
public class Messages {
    @Id
    private String id;
    private String conversationId;
    private UUID senderId;
    private String content;
    private MediaAttachment attachment;
    private Instant createdAt;
    private String status;

}
