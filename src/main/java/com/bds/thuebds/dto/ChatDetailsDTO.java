package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDetailsDTO {
    private LinkedHashSet<Long> chatId;
    private String receiverName;
    private List<ChatMessageDTO> messages;
}