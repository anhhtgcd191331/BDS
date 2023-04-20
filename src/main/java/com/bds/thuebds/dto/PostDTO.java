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
//	private List<Object> imageUrls;
//	private List<Object> videoUrls;
	private List<String> imageUrls;
	private List<String> videoUrls;
	private boolean isSold;
	private String typeOfApartment;
	private Long authorId;
	private Integer totalLike;
	private boolean isDeleted;
	private List<CommentPostDTO> commentPostDTOList;

	public PostDTO(Long postId, String postTitle, String description, Integer numberOfRooms, Double squareArea, Double price, String detailsAddress, boolean isSold, String typeOfApartment, Long authorId, Integer totalLike) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.description = description;
		this.numberOfRooms = numberOfRooms;
		this.squareArea = squareArea;
		this.price = price;
		this.detailsAddress = detailsAddress;
		this.isSold = isSold;
		this.typeOfApartment = typeOfApartment;
		this.authorId = authorId;
		this.totalLike = totalLike;
	}
}
