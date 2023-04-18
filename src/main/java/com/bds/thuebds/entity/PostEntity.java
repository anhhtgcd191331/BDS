package com.bds.thuebds.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_apartment")
public class PostEntity extends BaseEntity {
	@Column(name = "post_title")
	@NotNull
	private String postTitle;

	@Column(name = "author_id")
	private Long authorId;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "number_of_rooms")
	@NotNull
	private Integer numberOfRooms;

	@Column(name = "square_area")
	@NotNull
	private Double squareArea;

	@NotNull
	private Double price;

	@NotNull
	private String detailsAddress;

	@Column(name = "image_url_list")
	@ElementCollection
	private List<String> imageUrls;

	@Column(name = "video_url_list")
	@ElementCollection
	private List<String> videoUrls;

	@Column(name = "is_sold")
	@NotNull
	private boolean isSold;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "total_like")
	private Integer totalLike;

	@ManyToOne
	@JoinColumn(name = "apartment_type_id", nullable = false)
	private ApartmentTypeEntity apartmentType;
}
