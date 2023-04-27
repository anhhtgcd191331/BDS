package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.dto.IChatDetails;
import com.bds.thuebds.entity.ChatEntity;
import com.bds.thuebds.mapper.ChatMapper;
import com.bds.thuebds.repository.ChatRepository;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {
    @Autowired
    ChatRepository repository;

    @Override
    public List<ChatMessageDTO> getChatListBySenderId(Long senderId) {
        List<IChatDetails> iChatDetails = repository.getMessageListBySenderId(senderId);
        ChatMessageDTO chatDTO = new ChatMessageDTO();
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        for (IChatDetails iChatDetail : iChatDetails) {
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedAt(iChatDetail.getCreatedAt());
            chatDTO.setUpdatedAt(iChatDetail.getUpdatedAt());
            chatDTOList.add(chatDTO);
        }
        return chatDTOList;
    }

    @Override
    public List<ChatMessageDTO> getChatListByReceiverId(Long receiverId) {
        List<IChatDetails> iChatDetails = repository.getMessageListByReceiverId(receiverId);
        ChatMessageDTO chatDTO = new ChatMessageDTO();
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        for (IChatDetails iChatDetail : iChatDetails) {
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedAt(iChatDetail.getCreatedAt());
            chatDTO.setUpdatedAt(iChatDetail.getUpdatedAt());
            chatDTOList.add(chatDTO);
        }
        return chatDTOList;
    }

    @Override
    public List<ChatMessageDTO> getChatListByChatId(Long chatId) {
        List<IChatDetails> iChatDetails = repository.getMessageListByChatId(chatId);
        ChatMessageDTO chatDTO = new ChatMessageDTO();
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        for (IChatDetails iChatDetail : iChatDetails) {
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedAt(iChatDetail.getCreatedAt());
            chatDTO.setUpdatedAt(iChatDetail.getUpdatedAt());
            chatDTOList.add(chatDTO);
        }
        return chatDTOList;
    }

    @Override
    public ChatMessageDTO newRoomChat(Long senderId, Long receiverId) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setChatSenderId(senderId);
        chatEntity.setChatReceiverId(receiverId);

        ChatEntity savedChatEntity = repository.save(chatEntity);

        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setSenderId(savedChatEntity.getChatSenderId());
        chatMessageDTO.setReceiverId(savedChatEntity.getChatReceiverId());
        chatMessageDTO.setChatId(savedChatEntity.getId());

        return chatMessageDTO;
    }

}
