package org.example.messageservice.service;

import org.example.messageservice.models.dto.ConversationRecord;
import org.example.messageservice.models.dto.ConversationRecord.*;
import org.example.messageservice.models.entity.Conversation;

import java.util.List;
import java.util.UUID;

public interface IConversationService {

    Conversation createConversation (CreateConversation conversation);

    boolean deleteConversation (String conversationId);

    boolean acceptConversation (String conversationId);

    List<Conversation> getConversations (UUID userId);

    List<Conversation> getConversationRequest (UUID userId);
}
