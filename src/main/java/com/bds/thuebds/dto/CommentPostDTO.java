package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentPostDTO {
	private Long commentPostId;
	private Long userId;
	private Long postId;
	private String comment;
	private Date createdDate;
}
