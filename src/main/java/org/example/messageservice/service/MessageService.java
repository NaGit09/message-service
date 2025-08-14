package org.example.messageservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.messageservice.models.entity.Conversation;
import org.example.messageservice.models.entity.Messages;
import org.example.messageservice.models.repository.ConversationRepository;
import org.example.messageservice.models.repository.MessageRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService implements IMessageService {
    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;


    @Override
    public Messages sendMessage(Messages msg) {

        msg.setCreatedAt(Instant.now());
        Messages saved = messageRepository.save(msg);

        Optional<Conversation> conversation = conversationRepository
                .findById(msg.getConversationId());

        if (conversation.isPresent()) {
            Conversation c = conversation.get();
            c.setLastMessageId(msg.getId());
            conversationRepository.save(c);
        }
        String destination = "/topic/conversations/" + msg.getConversationId();
        template.convertAndSend(destination, saved);
        log.info(saved.toString());
        return saved;
    }

    @Override
    public List<Messages> getConversationMessages
            (String conversationId) {

        return messageRepository
                .findByConversationIdOrderByCreatedAtAsc(conversationId);
    }
}
