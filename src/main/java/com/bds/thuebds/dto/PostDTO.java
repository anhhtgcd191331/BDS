package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {
	private Long postId;
	private String postTitle;
	private String description;
	private Integer numberOfRooms;
	private Double squareArea;
	private Double price;
	private String detailsAddress;
	private String imageUrl;
	private String videoUrl;
	private boolean isSold;
	private String typeOfApartment;
	private Long authorId;
	private Integer totalLike;
	private boolean isDeleted;
	private List<CommentPostDTO> commentPostDTOList;
}
