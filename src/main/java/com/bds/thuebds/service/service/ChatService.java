package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.dto.IChatDetails;
import com.bds.thuebds.entity.ChatEntity;
import com.bds.thuebds.mapper.ChatMapper;
import com.bds.thuebds.repository.ChatRepository;
import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {
    @Autowired
    ChatRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ChatDetailsDTO> getChatListBySenderId(Long senderId) {
        List<IChatDetails> iChatDetails = repository.getMessageListBySenderId(senderId);

        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        LinkedHashSet<Long> chatIds = new LinkedHashSet<>();
        for (IChatDetails chatDetails : iChatDetails) {
            chatIds.add(chatDetails.getChatId());
        }
        for (IChatDetails iChatDetail : iChatDetails) {
            ChatMessageDTO chatDTO = new ChatMessageDTO();

//            messages
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedDate(iChatDetail.getCreatedDate());
            chatDTOList.add(chatDTO);
//            others infor
            ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
            chatDetailsDTO.setChatId(chatIds);
            chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(chatDTO.getReceiverId()).getFullName());
            chatDetailsDTO.setMessages(chatDTOList);
            chatDetailsList.add(chatDetailsDTO);
        }
        return chatDetailsList;
    }

    @Override
    public List<ChatDetailsDTO> getChatListByReceiverId(Long receiverId) {
        List<IChatDetails> iChatDetails = repository.getMessageListByReceiverId(receiverId);
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        LinkedHashSet<Long> chatIds = new LinkedHashSet<>();
        for (IChatDetails chatDetails : iChatDetails) {
            chatIds.add(chatDetails.getChatId());
        }
        for (IChatDetails iChatDetail : iChatDetails) {
            ChatMessageDTO chatDTO = new ChatMessageDTO();
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedDate(iChatDetail.getCreatedDate());
            chatDTOList.add(chatDTO);

            ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
            chatDetailsDTO.setChatId(chatIds);
            chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(chatDTO.getReceiverId()).getFullName());
            chatDetailsDTO.setMessages(chatDTOList);
            chatDetailsList.add(chatDetailsDTO);
        }
        return chatDetailsList;
    }

    @Override
    public List<ChatDetailsDTO> getChatListByChatId(Long chatId) {
        List<IChatDetails> iChatDetails = repository.getMessageListByChatId(chatId);
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        LinkedHashSet<Long> chatIds = new LinkedHashSet<>();
        for (IChatDetails chatDetails : iChatDetails) {
            chatIds.add(chatDetails.getChatId());
        }
        for (IChatDetails iChatDetail : iChatDetails) {
            ChatMessageDTO chatDTO = new ChatMessageDTO();
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getReceiverId());
            chatDTO.setCreatedDate(iChatDetail.getCreatedDate());
            chatDTOList.add(chatDTO);
            ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
            chatDetailsDTO.setChatId(chatIds);
            chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(chatDTO.getReceiverId()).getFullName());
            chatDetailsDTO.setMessages(chatDTOList);
            chatDetailsList.add(chatDetailsDTO);
        }
        return chatDetailsList;
    }

    @Override
    public ChatMessageDTO newRoomChat(Long senderId, Long receiverId) {
        ChatEntity chatCheck = repository.getChatEntityByChatReceiverIdAndChatSenderId(receiverId, senderId);
        ChatEntity chatEntity = new ChatEntity();
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        if (chatCheck == null) {
            chatEntity.setChatSenderId(senderId);
            chatEntity.setChatReceiverId(receiverId);
            ChatEntity savedChatEntity = repository.saveAndFlush(chatEntity);
            chatMessageDTO.setSenderId(savedChatEntity.getChatSenderId());
            chatMessageDTO.setReceiverId(savedChatEntity.getChatReceiverId());
            chatMessageDTO.setChatId(savedChatEntity.getId());
        } else {
            chatMessageDTO.setSenderId(chatCheck.getChatSenderId());
            chatMessageDTO.setReceiverId(chatCheck.getChatReceiverId());
            chatMessageDTO.setChatId(chatCheck.getId());
        }
        return chatMessageDTO;
    }


}
