package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.CommentPostDTO;
import com.bds.thuebds.entity.CommentPostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentPostMapper {
	CommentPostDTO entityToDTO(CommentPostEntity commentPostEntity);

	CommentPostEntity dtoToEntity(CommentPostDTO commentPostDTO);

	CommentPostEntity updateCommentPost(@MappingTarget CommentPostEntity oldComment, CommentPostEntity newCommentPostEntity);
}
