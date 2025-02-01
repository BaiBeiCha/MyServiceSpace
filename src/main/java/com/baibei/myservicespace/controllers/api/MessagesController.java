package com.baibei.myservicespace.controllers.api;

import com.baibei.myservicespace.dto.ProductCard;
import com.baibei.myservicespace.models.Message;
import com.baibei.myservicespace.models.Product;
import com.baibei.myservicespace.repositories.MessagesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesRepository messagesRepository;

    public MessagesController(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @GetMapping
    public List<Message> getMessages() {
        return (List<Message>) messagesRepository.findAll();
    }

    @PostMapping
    public Message addProduct(@RequestBody Message message) {
        messagesRepository.save(message);
        return message;
    }

    @PatchMapping
    public Message updateProduct(@RequestBody Message message) {
        Message newMessage = messagesRepository.findById(message.getId()).orElse(null);

        if (newMessage != null) {
            if (message.getEmail() != null) {
                newMessage.setEmail(message.getEmail());
            }
            if (message.getName() != null) {
                newMessage.setName(message.getName());
            }
            if (message.getContent() != null) {
                newMessage.setContent(message.getContent());
            }
        } else {
            newMessage = message;
        }

        messagesRepository.save(newMessage);
        return newMessage;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        Message message = messagesRepository.findById(id).orElse(null);

        if (message != null) {
            messagesRepository.delete(message);
            return "Successfully deleted message " + id;
        } else {
            return "Message " + id + " not found";
        }
    }
}
