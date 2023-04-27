package com.bds.thuebds.controller;

import com.bds.thuebds.dto.ChatDTO;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    IChatService service;

    @GetMapping("/get")
    public List<ChatDTO> getChatByChatId(@RequestParam(name = "chatId") Long receiverId) {
        return service.getChatEntitiesByChatId(receiverId);
    }

    @GetMapping("/sender")
    public List<ChatDTO> getChatBySenderId(@RequestParam(name = "senderId") Long senderId) {
        return service.getChatEntitiesBySenderId(senderId);
    }
}
