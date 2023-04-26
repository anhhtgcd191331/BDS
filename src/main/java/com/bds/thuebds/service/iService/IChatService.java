package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.ChatDTO;

import java.util.List;

public interface IChatService {
    List<ChatDTO> getChatByReceiverId(Long receiverId);

    List<ChatDTO> getChatBySenderId(Long senderId);
}
