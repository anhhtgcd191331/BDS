package com.bds.thuebds.controller;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    IChatService service;

    @PostMapping("/new/room")
    public ChatMessageDTO newRoomChat(@RequestParam(name = "senderId") Long senderId,
                                      @RequestParam(name = "receiverId") Long receiverId) {
        return service.newRoomChat(senderId, receiverId);
    }

    @GetMapping("/get")
    public ChatDetailsDTO getChatByChatId(@RequestParam(name = "chatId") Long chatId) {
        return service.getChatListByChatId(chatId);
    }

    @GetMapping("/sender")
    public List<ChatDetailsDTO> getChatBySenderId(@RequestParam(name = "senderId") Long senderId) {
        return service.getChatListBySenderId(senderId);
    }

    @GetMapping("/receiver")
    public List<ChatDetailsDTO> getChatByReceiverId(@RequestParam(name = "receiverId") Long receiverId) {
        return service.getChatListByReceiverId(receiverId);
    }

}
