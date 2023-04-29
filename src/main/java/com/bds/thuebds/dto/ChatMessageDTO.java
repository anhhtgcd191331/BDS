package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessageDTO {
    private Long receiverId;
    private String receiverName;
    private Long senderId;
    private String content;
    private Timestamp createdDate;
    private Long chatId;
}
