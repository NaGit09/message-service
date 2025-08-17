package org.example.messageservice.models.repository;

import org.example.messageservice.enums.CONVERSATION_STATUS;
import org.example.messageservice.models.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    List<Conversation> findByStatusAndParticipantsContaining
            (CONVERSATION_STATUS status, List<UUID> participants);

}
