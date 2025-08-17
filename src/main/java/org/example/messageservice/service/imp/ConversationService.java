package org.example.messageservice.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.messageservice.enums.CONVERSATION_STATUS;
import org.example.messageservice.models.dto.ConversationRecord.CreateConversation;
import org.example.messageservice.models.entity.Conversation;
import org.example.messageservice.models.repository.ConversationRepository;
import org.example.messageservice.models.repository.MessageRepository;
import org.example.messageservice.service.IConversationService;
import org.example.messageservice.utils.ConversationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConversationService implements IConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    @Override
    public Conversation createConversation(CreateConversation conversation) {

        Conversation newConversation = ConversationMapper.initialConversation(conversation);

        messageRepository.save(conversation.lastMessage());

        return conversationRepository.save(newConversation);
    }



    @Override
    public boolean acceptConversation(String conversationId) {
        return handlerConversation(conversationId, c -> {
            c.setStatus(CONVERSATION_STATUS.ACCEPT);
                conversationRepository.save(c);
        } ,  "Conversation with id {} accepted!");
    }

    @Override
    public boolean deleteConversation(String conversationId) {

        return handlerConversation
                (conversationId , conversationRepository::delete
                        , "Conversation with id {} deleted!");
    }

    @Override
    public List<Conversation> getConversations(UUID userId) {

        return conversationRepository.findByStatusAndParticipantsContaining
                (CONVERSATION_STATUS.ACCEPT, List.of(userId));
    }

    @Override
    public List<Conversation> getConversationRequest(UUID userId) {
        return conversationRepository.findByStatusAndParticipantsContaining
                (CONVERSATION_STATUS.PENDING, List.of(userId));
    }


    private boolean handlerConversation
            (String conversationId, Consumer<Conversation> consumer , String successMessage) {
            return conversationRepository.findById(conversationId).map(
                    c -> {
                        consumer.accept(c);
                        log.info(successMessage);
                        return true;
                    }
            ).orElseGet( () -> {
                log.debug("conversation with id {} not found",  conversationId);
                return  false;
            });
    }
}
