package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "comment_post")
public class CommentPostEntity extends BaseEntity {
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "post_id")
	private Long postId;

	private String comment;
}
