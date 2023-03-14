package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.dto.SearchDTO;
import com.bds.thuebds.entity.ApartmentTypeEntity;
import com.bds.thuebds.entity.PostEntity;
import com.bds.thuebds.mapper.PostMapper;
import com.bds.thuebds.repository.ApartmentTypeRepository;
import com.bds.thuebds.repository.PostApartmentRepository;
import com.bds.thuebds.service.iService.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchService implements ISearchService {

	@Autowired
	PostApartmentRepository postRepository;

	@Autowired
	PostMapper postMapper;

	@Autowired
	ApartmentTypeRepository apartmentTypeRepository;

	@Override
	public List<PostDTO> searchPost(SearchDTO searchDTO) {
		ApartmentTypeEntity apartmentTypeEntity = null;
		if (apartmentTypeRepository.getApartmentTypeEntityByTypeOfApartment(searchDTO.getApartmentType()) != null) {
			apartmentTypeRepository.getApartmentTypeEntityByTypeOfApartment(searchDTO.getApartmentType());
		}

		ExampleMatcher matcher = ExampleMatcher
			.matchingAny()
			.withIgnorePaths("numberOfRoom").withIgnoreNullValues();

		PostEntity post = PostEntity
			.builder()
			.postTitle(searchDTO.getTitle())
			.detailsAddress(searchDTO.getDistrict())
			.numberOfRooms(searchDTO.getNumberOfRooms())
			.apartmentType(apartmentTypeEntity)
			.price(searchDTO.getPrice())
			.squareArea(searchDTO.getSquareArea())
			.build();

		return postRepository.findAll(Example.of(post, matcher))
			.stream()
			.map(postEntity -> postMapper.entityToDto(postEntity))
			.filter(postDTO -> !postDTO.isSold())
			.sorted(Comparator.comparing(PostDTO::getTotalLike))
			.collect(Collectors.toList());
	}

}
