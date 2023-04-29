package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;

import java.util.List;

public interface IChatService {

    List<ChatDetailsDTO> getChatListBySenderId(Long senderId);

    List<ChatDetailsDTO> getChatListByReceiverId(Long receiverId);

    ChatDetailsDTO getChatListByChatId(Long chatId);

    ChatMessageDTO newRoomChat(Long senderId, Long receiverId);
}

