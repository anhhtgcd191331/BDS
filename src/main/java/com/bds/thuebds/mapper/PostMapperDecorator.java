package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.entity.PostEntity;
import com.bds.thuebds.repository.ApartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class PostMapperDecorator implements PostMapper {
	@Autowired
	@Qualifier("delegate")
	private PostMapper delegate;

	@Autowired
	private ApartmentTypeRepository apartmentTypeRepository;

	@Override
	public PostDTO entityToDto(PostEntity postEntity) {
		PostDTO postDTO = delegate.entityToDto(postEntity);
		postDTO.setTypeOfApartment(postEntity.getApartmentType().getTypeOfApartment());
		return postDTO;
	}

	@Override
	public PostEntity dtoToEntity(PostDTO postDTO) {
		PostEntity postEntity = delegate.dtoToEntity(postDTO);
		postEntity.setApartmentType(apartmentTypeRepository.getApartmentTypeEntityByTypeOfApartment(postDTO.getTypeOfApartment()));
		return postEntity;
	}

}
