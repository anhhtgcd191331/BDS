package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.ChatMessageDTO;

import java.util.List;

public interface IChatService {
    List<ChatMessageDTO> getChatListBySenderId(Long senderId);

    List<ChatMessageDTO> getChatListByReceiverId(Long receiverId);

    List<ChatMessageDTO> getChatListByChatId(Long chatId);

    ChatMessageDTO newRoomChat(Long senderId, Long receiverId);
}
