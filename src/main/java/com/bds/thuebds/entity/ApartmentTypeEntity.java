package com.bds.thuebds.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "apartment_type")
public class ApartmentTypeEntity extends BaseEntity{
	@Column(name = "type_of_apartment")
	private String typeOfApartment;

	@OneToMany(mappedBy = "apartmentType")
	private List<PostEntity> postEntityList;
}
