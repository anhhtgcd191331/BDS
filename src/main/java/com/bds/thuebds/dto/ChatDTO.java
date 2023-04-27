package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    private Long id;
    private String content;
    private ZonedDateTime createdDate;
    private ZonedDateTime lastedModifiedDate;
    private Long chatId;
    private Long senderId;
}
