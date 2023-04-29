package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.entity.PostEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
@DecoratedWith(PostMapperDecorator.class)
public interface PostMapper {
	@Mapping(target = "postId", source = "id")
	PostDTO entityToDto(PostEntity postEntity);
	PostEntity dtoToEntity(PostDTO postDTO);

	@Mapping(target = "id", ignore = true)
	PostEntity updatePost(@MappingTarget PostEntity oldPost, PostEntity newPost);
}
