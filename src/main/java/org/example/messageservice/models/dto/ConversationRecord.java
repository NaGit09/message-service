package org.example.messageservice.models.dto;

import lombok.NonNull;
import org.example.messageservice.models.entity.Messages;

import java.util.List;
import java.util.UUID;

public class ConversationRecord {

    public record CreateConversation(
           @NonNull List<UUID> participants,
            Boolean isGroup
            , Messages lastMessage) {}


}
