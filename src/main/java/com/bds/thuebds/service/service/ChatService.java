package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ChatDTO;
import com.bds.thuebds.mapper.ChatMapper;
import com.bds.thuebds.repository.ChatRepository;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {
    @Autowired
    ChatRepository repository;

    @Autowired
    ChatMapper mapper;

    @Override
    public List<ChatDTO> getChatByReceiverId(Long receiverId) {
        return repository.getChatEntitiesByChatReceiverId(receiverId)
                .stream().map(chatEntity -> mapper.entityToDto(chatEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDTO> getChatBySenderId(Long senderId) {
        return repository.getChatEntitiesByChatSenderId(senderId)
                .stream().map(chatEntity -> mapper.entityToDto(chatEntity))
                .collect(Collectors.toList());
    }
}
