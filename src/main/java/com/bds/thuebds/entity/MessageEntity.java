package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "chat_message")

public class MessageEntity extends BaseEntity{
    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Time createdAt;

    @Column(name = "updated_at")
    private Time updatedAt;

    @Column(name = "chat_id")
    private Long chatId;
}
