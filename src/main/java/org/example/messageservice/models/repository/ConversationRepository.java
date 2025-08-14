package org.example.messageservice.models.repository;

import org.example.messageservice.models.entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Optional<Conversation> findByParticipantsInAndGroupIsFalse(Collection<List<UUID>> participants, boolean group);
}
