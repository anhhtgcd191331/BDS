package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;

import java.util.List;

public interface IChatService {
//    List<ChatMessageDTO> getChatListBySenderId(Long senderId);
//
//    List<ChatMessageDTO> getChatListByReceiverId(Long receiverId);
//
//    List<ChatMessageDTO> getChatListByChatId(Long chatId);
//
//    ChatMessageDTO newRoomChat(Long senderId, Long receiverId);
List<ChatDetailsDTO> getChatListBySenderId(Long senderId);
    List<ChatDetailsDTO> getChatListByReceiverId(Long receiverId);
    List<ChatDetailsDTO> getChatListByChatId(Long chatId);

    ChatMessageDTO newRoomChat(Long senderId, Long receiverId);
}
