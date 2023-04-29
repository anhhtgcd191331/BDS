package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessageDTO {
    private Long receiverId;
    private Long senderId;
    private String content;
    private Timestamp createdDate;
    private Long chatId;
}
