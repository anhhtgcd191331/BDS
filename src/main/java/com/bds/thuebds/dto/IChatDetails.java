package com.bds.thuebds.dto;

import java.sql.Time;
import java.sql.Timestamp;

public interface IChatDetails {
    Long getReceiverId();
    Long getSenderId();
    String getContent();
    Timestamp getCreatedDate();
    Long getChatId();
}
