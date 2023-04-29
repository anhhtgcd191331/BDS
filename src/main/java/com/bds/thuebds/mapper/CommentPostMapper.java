package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.CommentPostDTO;
import com.bds.thuebds.entity.CommentPostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentPostMapper {

	@Mapping(target = "commentPostId", source = "id")
	@Mapping(target = "createdDate", source = "createdDate")
	CommentPostDTO entityToDTO(CommentPostEntity commentPostEntity);

	@Mapping(target = "id", source = "commentPostId")
	CommentPostEntity dtoToEntity(CommentPostDTO commentPostDTO);

	CommentPostEntity updateCommentPost(@MappingTarget CommentPostEntity oldComment, CommentPostEntity newCommentPostEntity);
}
