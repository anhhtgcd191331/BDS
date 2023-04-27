package com.bds.thuebds.repository;

import com.bds.thuebds.dto.IChatDetails;
import com.bds.thuebds.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query(value = " SELECT * FROM chat_message " +
            " INNER JOIN chat_message.id = chat.id " +
            " GROUP BY chat_message.id" +
            " WHERE chat_id =:chatId ", nativeQuery = true)
    List<IChatDetails> getMessageListByChatId(@Param("chatId") Long chatId);
    @Query(value = " SELECT * FROM chat_message " +
            " INNER JOIN chat_message.id = chat.id " +
            " GROUP BY chat_message.id" +
            " WHERE chat_message.sender_id =:senderId ", nativeQuery = true)
    List<IChatDetails> getMessageListBySenderId(@Param("senderId") Long senderId);

    @Query(value = " SELECT * FROM chat_message " +
            " INNER JOIN chat_message.id = chat.id " +
            " GROUP BY chat_message.id" +
            " WHERE chat.chat_receiver_id =:receiverId ", nativeQuery = true)
    List<IChatDetails> getMessageListByReceiverId(@Param("receiverId") Long receiverId);
}
