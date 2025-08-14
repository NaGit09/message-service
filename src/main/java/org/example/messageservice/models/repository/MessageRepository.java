package org.example.messageservice.models.repository;

import org.example.messageservice.models.entity.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Messages, String> {
    @Query("{ $or: [ { $and: [ { 'senderId': ?0 }, { 'conversationId': ?1 } ] }, { $and: [ { 'senderId': ?2 }, { 'conversationId': ?1 } ] } ] }")
    List<Messages> findConversationMessages(String userA, String conversationId, String userB);

    List<Messages> findByConversationIdOrderByCreatedAtAsc(String conversationId);
}
