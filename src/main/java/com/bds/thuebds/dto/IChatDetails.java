package com.bds.thuebds.dto;

import java.sql.Timestamp;

public interface IChatDetails {
    Long getChatReceiverId();

    Long getSenderId();

    String getContent();

    Timestamp getCreatedDate();

    Long getChatId();
}
