package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ChatDetailsDTO;
import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.dto.IChatDetails;
import com.bds.thuebds.entity.ChatEntity;
import com.bds.thuebds.repository.ChatRepository;
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
        ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
        List<ChatDetailsDTO> chatDetailsList = new ArrayList<>();
        List<ChatMessageDTO> chatDTOList = new ArrayList<>();
//        query big list
        List<IChatDetails> iChatDetailsList = repository.getMessageListBySenderId(senderId);
//        get chat id unique list
        LinkedHashSet<Long> chatIdUniqueList = new LinkedHashSet<>();
        for (IChatDetails iChatDetails : iChatDetailsList) {
            chatIdUniqueList.add(iChatDetails.getChatId());
        }
//        create list chat details list
        for (Long i : chatIdUniqueList) {
            List<IChatDetails> msgBySenderIdAndChatId = repository.getMessageListBySenderIdAndChatId(senderId, i);
            chatDetailsDTO.setChatId(i);
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
            chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(senderId).getFullName());
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
        for (int i = 0; i < chatIds.size(); i++) {
            for (IChatDetails iChatDetail : iChatDetails) {
                ChatMessageDTO chatDTO = new ChatMessageDTO();
//            messages
                chatDTO.setChatId(iChatDetail.getChatId());
                chatDTO.setSenderId(iChatDetail.getSenderId());
                chatDTO.setContent(iChatDetail.getContent());
                chatDTO.setReceiverId(iChatDetail.getChatReceiverId());
                chatDTO.setCreatedDate(iChatDetail.getCreatedDate());
                chatDTOList.add(chatDTO);
//            others infor
                ChatDetailsDTO chatDetailsDTO = new ChatDetailsDTO();
                chatDetailsDTO.setChatId(iChatDetail.getChatId());
                chatDetailsDTO.setReceiverName(userRepository.getUserEntityByUserId(chatDTO.getReceiverId()).getFullName());
                chatDetailsDTO.setSenderName(userRepository.getUserEntityByUserId(chatDTO.getSenderId()).getFullName());
                chatDetailsDTO.setMessages(chatDTOList);
                chatDetailsList.add(chatDetailsDTO);
            }
        }
        return chatDetailsList;
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
