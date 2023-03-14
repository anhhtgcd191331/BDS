package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchDTO {
	private String title;
	private String district;
	private Integer numberOfRooms;
	private String apartmentType;
	private Double price;
	private Double squareArea;
}
