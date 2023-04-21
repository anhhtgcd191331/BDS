package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {
	@Column(name = "sender_id")
	private Long senderId;
	@Column(name = "receiver_id")
	private Long receiverId;
	@Column(name = "post_id")
	private Long postId;
	@Column(name = "notification_content")
	private String notificationContent;
	@Column(name = "is_read")
	private boolean isRead;
}
