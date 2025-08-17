package org.example.messageservice.service;

import org.example.messageservice.models.entity.Messages;

import java.util.List;

public interface IMessageService {

    Messages sendMessage(Messages msg);

    List<Messages> getConversationMessages(String conversationId);
}
