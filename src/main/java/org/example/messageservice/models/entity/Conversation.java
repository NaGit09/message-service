package org.example.messageservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.messageservice.enums.CONVERSATION_STATUS;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conversations")
public class Conversation {
    @Id
    private String id;
    // check if conversation is not groups
    private List<UUID> participants;

    private boolean group;

    private CONVERSATION_STATUS status;
    // meta data
    private Instant createdAt;
    // load last message
    private Messages lastMessages;
}