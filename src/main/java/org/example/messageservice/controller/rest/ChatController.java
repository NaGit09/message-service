package org.example.messageservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.messageservice.controller.api.IChatController;
import org.example.messageservice.models.entity.Messages;
import org.example.messageservice.service.MessageService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController implements IChatController {
    private final MessageService messageService;


    @Override
    public Messages sendMessage(Messages message) {

        return messageService.sendMessage(message);

    }
}
