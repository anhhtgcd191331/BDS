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
    @Query(value = "SELECT cm.id,\n" +
            "       chat_id          AS chatId,\n" +
            "       sender_id        AS senderId,\n" +
            "       content,\n" +
            "       chat_receiver_id AS chatReceiverId,\n" +
            "       cm.createddate AS createdDate\n" +
            "FROM chat\n" +
            "         INNER JOIN\n" +
            "     chat_message cm\n" +
            "     on chat.id = cm.chat_id\n" +
            "WHERE chat_id =:chatId\n" +
            "GROUP BY chat_id, sender_id, content, chat_receiver_id, created_at, updated_at, cm.id", nativeQuery = true)
    List<IChatDetails> getMessageListByChatId(@Param("chatId") Long chatId);

    @Query(value = "SELECT cm.id,\n" +
            "       chat_id          AS chatId,\n" +
            "       sender_id        AS senderId,\n" +
            "       content,\n" +
            "       chat_receiver_id AS chatReceiverId,\n" +
            "       cm.createddate AS createdDate\n" +
            "FROM chat\n" +
            "         INNER JOIN\n" +
            "     chat_message cm\n" +
            "     on chat.id = cm.chat_id\n" +
            "WHERE chat_sender_id =:senderId\n" +
            "GROUP BY chat_id, sender_id, content, chat_receiver_id, created_at, updated_at, cm.id", nativeQuery = true)
    List<IChatDetails> getMessageListBySenderId(@Param("senderId") Long senderId);

    @Query(value = "SELECT cm.id,\n" +
            "       chat_id          AS chatId,\n" +
            "       sender_id        AS senderId,\n" +
            "       content,\n" +
            "       chat_receiver_id AS chatReceiverId,\n" +
            "       cm.createddate AS createdDate\n" +
            "FROM chat\n" +
            "         INNER JOIN\n" +
            "     chat_message cm\n" +
            "     on chat.id = cm.chat_id\n" +
            "WHERE chat_receiver_id =:receiverId\n" +
            "GROUP BY chat_id, sender_id, content, chat_receiver_id, created_at, updated_at, cm.id", nativeQuery = true)
    List<IChatDetails> getMessageListByReceiverId(@Param("receiverId") Long receiverId);

    ChatEntity getChatEntityByChatReceiverIdAndChatSenderId(Long chatReceiverId, Long chatSenderId);

    @Query(value = "SELECT cm.id,\n" +
            "       chat_id          AS chatId,\n" +
            "       sender_id        AS senderId,\n" +
            "       content,\n" +
            "       chat_receiver_id AS chatReceiverId,\n" +
            "       cm.createddate AS createdDate\n" +
            "FROM chat\n" +
            "         INNER JOIN\n" +
            "     chat_message cm\n" +
            "     on chat.id = cm.chat_id\n" +
            "WHERE sender_id =:senderId AND chat_id =:chatId\n" +
            "GROUP BY chat_id, sender_id, chat_receiver_id, created_at, updated_at, cm.id", nativeQuery = true)
    List<IChatDetails> getMessageListBySenderIdAndChatId(@Param("senderId") Long senderId,
                                                         @Param("chatId") Long chatId);

    @Query(value = "SELECT cm.id,\n" +
            "       chat_id          AS chatId,\n" +
            "       sender_id        AS senderId,\n" +
            "       content,\n" +
            "       chat_receiver_id AS chatReceiverId,\n" +
            "       cm.createddate AS createdDate\n" +
            "FROM chat\n" +
            "         INNER JOIN\n" +
            "     chat_message cm\n" +
            "     on chat.id = cm.chat_id\n" +
            "WHERE chat_receiver_id =:receiverId AND chat_id =:chatId\n" +
            "GROUP BY chat_id, sender_id, chat_receiver_id, created_at, updated_at, cm.id", nativeQuery = true)
    List<IChatDetails> getMessageListByReceiverIdAndChatId(@Param("receiverId") Long receiverId,
                                                           @Param("chatId") Long chatId);
}