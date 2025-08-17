package org.example.messageservice.utils;

import org.example.messageservice.enums.CONVERSATION_STATUS;
import org.example.messageservice.models.dto.ConversationRecord.*;
import org.example.messageservice.models.entity.Conversation;

import java.time.Instant;


public class ConversationMapper {
    // initial new conversation
    public static Conversation initialConversation (CreateConversation conversation) {
        return Conversation.builder()
                .group(conversation.isGroup())
                .status(CONVERSATION_STATUS.PENDING)
                .participants(conversation.participants())
                .lastMessages(conversation.lastMessage())
                .createdAt(Instant.now())
                .build();
    }

}
