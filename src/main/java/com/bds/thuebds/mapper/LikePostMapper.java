package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.LikePostDTO;
import com.bds.thuebds.entity.LikePostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LikePostMapper {
	@Mapping(target = "likePostId", source = "id")
	LikePostDTO entityToDTO(LikePostEntity likePostEntity);

	@Mapping(target = "id", source = "likePostId")
	LikePostEntity dtoToEntity(LikePostDTO likePostDTO);

	LikePostEntity updateLike(@MappingTarget LikePostEntity oldLike, LikePostEntity newLike);
}
