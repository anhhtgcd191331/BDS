package com.bds.thuebds.dto;

import java.sql.Time;

public interface IChatDetails {
    Long getReceiverId();
    Long getSenderId();
    String getContent();
    Time getCreatedAt();
    Time getUpdatedAt();
    Long getChatId();
}
