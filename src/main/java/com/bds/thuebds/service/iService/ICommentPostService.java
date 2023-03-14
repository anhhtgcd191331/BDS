package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.CommentPostDTO;

import java.util.List;

public interface ICommentPostService {
	CommentPostDTO saveNewComment(CommentPostDTO commentPostDTO);
	CommentPostDTO updateComment(CommentPostDTO commentPostDTO);
	void deleteComment(CommentPostDTO commentPostDTO);
	List<CommentPostDTO> getAllCommentOfPost(Long postId);
}
