package com.bds.thuebds.repository;

import com.bds.thuebds.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> getChatEntitiesByChatSenderId(Long senderId);

    List<ChatEntity> getChatEntitiesByChatReceiverId(Long receiverId);
}
