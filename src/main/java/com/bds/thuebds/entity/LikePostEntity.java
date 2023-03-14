package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "like_post")
public class LikePostEntity extends BaseEntity {
	private boolean isLike;

	private Long userId;

	private Long postId;
}
