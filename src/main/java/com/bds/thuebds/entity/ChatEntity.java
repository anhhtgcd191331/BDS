package com.bds.thuebds.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class ChatEntity extends BaseEntity{
	@Column(name = "chat_sender_id")
	private Long chatSenderId;
	@Column(name = "chat_receiver_id")
	private Long chatReceiverId;
	@Column(name = "chat_content")
	private String chatContent;
}
