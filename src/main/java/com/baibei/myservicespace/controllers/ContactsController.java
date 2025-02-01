package com.baibei.myservicespace.controllers;

import com.baibei.myservicespace.models.Message;
import com.baibei.myservicespace.repositories.MessagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

    private final MessagesRepository messagesRepository;

    public ContactsController(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @GetMapping
    public String contacts() {
        return "contacts";
    }

    @PostMapping
    public String sendMessage(Message message) {
        try {
            messagesRepository.save(message);
        } catch (Exception e) {
            System.out.println("Can't save message" + e.getMessage());
        }
        return "redirect:/contacts";
    }
}
