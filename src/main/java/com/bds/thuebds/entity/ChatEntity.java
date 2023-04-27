package com.bds.thuebds.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class ChatEntity {
	@Id
	private Long id;
	private String content;
	@Column(name = "createddate")
	private ZonedDateTime createdDate;
	@Column(name = "lasted_modified_date")
	private ZonedDateTime lastedModifiedDate;
	@Column(name = "chat_id")
	private Long chatId;
	@Column(name = "sender_id")
	private Long senderId;
}
