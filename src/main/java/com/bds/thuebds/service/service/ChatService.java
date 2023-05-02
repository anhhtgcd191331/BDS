package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.dto.IChatDetails;
import com.bds.thuebds.entity.ChatEntity;
import com.bds.thuebds.repository.ChatRepository;

import com.bds.thuebds.dto.ChatDTO;
import com.bds.thuebds.mapper.ChatMapper;

import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.service.iService.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class ChatService implements IChatService {
    @Autowired
    ChatRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ChatDetailsDTO> getChatListBySenderId(Long senderId) {
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        List<ChatDetailsDTO> chatDetailsDTOReturns = new ArrayList<>();
//        query big list
        List<IChatDetails> iChatDetailsList = repository.getMessageListBySenderId(senderId);
//        get chat id unique list
        LinkedHashSet<Long> chatIdUniqueList = new LinkedHashSet<>();
        for (IChatDetails iChatDetails : iChatDetailsList) {
            chatIdUniqueList.add(iChatDetails.getChatId());
        }
//        create list chat details list with id info only
        for (Long i : chatIdUniqueList) {
            ChatDetailsDTO chatDetailsTempDTO = new ChatDetailsDTO();
            chatDetailsTempDTO.setChatId(i);
            chatDetailsList.add(chatDetailsTempDTO);
        }
        for (ChatDetailsDTO chatDetails : chatDetailsList) {
            ChatDetailsDTO chatDetailsDTOReturn = new ChatDetailsDTO();
            List<ChatMessageDTO> chatDTOList = new ArrayList<>();
//        alter msg list object to chat details list
            List<IChatDetails> msgBySenderIdAndChatId = repository.getMessageListBySenderIdAndChatId(senderId, chatDetails.getChatId());
//            each item in chat details list has list of msg
            for (IChatDetails iChatDetails : msgBySenderIdAndChatId) {
                ChatMessageDTO chatDTO = new ChatMessageDTO();
//            messages
                chatDTO.setChatId(iChatDetails.getChatId());
                chatDTO.setSenderId(iChatDetails.getSenderId());
                chatDTO.setContent(iChatDetails.getContent());
                chatDTO.setReceiverId(iChatDetails.getChatReceiverId());
                chatDTO.setReceiverName(userRepository.getUserEntityByUserId(iChatDetails.getChatReceiverId()).getFullName());
                chatDTO.setCreatedDate(iChatDetails.getCreatedDate());
                chatDTOList.add(chatDTO);
            }
            chatDetailsDTOReturn.setChatId(chatDetails.getChatId());
            chatDetailsDTOReturn.setReceiverName(userRepository.getUserEntityByUserId(senderId).getFullName());
            chatDetailsDTOReturn.setMessages(chatDTOList);
            chatDetailsDTOReturns.add(chatDetailsDTOReturn);
        }
        return chatDetailsDTOReturns;
    }

    @Override
    public List<ChatDetailsDTO> getChatListByReceiverId(Long receiverId) {
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        List<ChatDetailsDTO> chatDetailsDTOReturns = new ArrayList<>();
//        query big list
        List<IChatDetails> iChatDetailsList = repository.getMessageListByReceiverId(receiverId);
//        get chat id unique list
        LinkedHashSet<Long> chatIdUniqueList = new LinkedHashSet<>();
        for (IChatDetails iChatDetails : iChatDetailsList) {
            chatIdUniqueList.add(iChatDetails.getChatId());
        }
//        create list chat details list with id info only
        for (Long i : chatIdUniqueList) {
            ChatDetailsDTO chatDetailsTempDTO = new ChatDetailsDTO();
            chatDetailsTempDTO.setChatId(i);
            chatDetailsList.add(chatDetailsTempDTO);
        }
        for (ChatDetailsDTO chatDetails : chatDetailsList) {
            ChatDetailsDTO chatDetailsDTOReturn = new ChatDetailsDTO();
            List<ChatMessageDTO> chatDTOList = new ArrayList<>();
//        alter msg list object to chat details list
            List<IChatDetails> msgByReceiverIdAndChatId = repository.getMessageListByReceiverIdAndChatId(receiverId, chatDetails.getChatId());
//            each item in chat details list has list of msg
            for (IChatDetails iChatDetails : msgByReceiverIdAndChatId) {
                ChatMessageDTO chatDTO = new ChatMessageDTO();
//            messages
                chatDTO.setChatId(iChatDetails.getChatId());
                chatDTO.setSenderId(iChatDetails.getSenderId());
                chatDTO.setContent(iChatDetails.getContent());
                chatDTO.setReceiverId(iChatDetails.getChatReceiverId());
                chatDTO.setReceiverName(userRepository.getUserEntityByUserId(iChatDetails.getChatReceiverId()).getFullName());
                chatDTO.setCreatedDate(iChatDetails.getCreatedDate());
                chatDTOList.add(chatDTO);
            }
            chatDetailsDTOReturn.setChatId(chatDetails.getChatId());
            chatDetailsDTOReturn.setReceiverName(userRepository.getUserEntityByUserId(receiverId).getFullName());
            chatDetailsDTOReturn.setMessages(chatDTOList);
            chatDetailsDTOReturns.add(chatDetailsDTOReturn);
        }
        return chatDetailsDTOReturns;
    }

    @Override
    public ChatDetailsDTO getChatListByChatId(Long chatId) {
        List<IChatDetails> iChatDetails = repository.getMessageListByChatId(chatId);
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
        ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
        for (IChatDetails iChatDetail : iChatDetails) {
            ChatMessageDTO chatDTO = new ChatMessageDTO();
            chatDTO.setChatId(iChatDetail.getChatId());
            chatDTO.setSenderId(iChatDetail.getSenderId());
            chatDTO.setContent(iChatDetail.getContent());
            chatDTO.setReceiverId(iChatDetail.getChatReceiverId());
            chatDTO.setCreatedDate(iChatDetail.getCreatedDate());
            chatDTOList.add(chatDTO);
            chatDetailsDTO.setChatId(chatId);
            chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(chatDTO.getReceiverId()).getFullName());
            chatDetailsDTO.setSenderName(userRepository.getUserEntityByUserId(chatDTO.getSenderId()).getFullName());
            chatDetailsDTO.setMessages(chatDTOList);
        }
        return chatDetailsDTO;
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
