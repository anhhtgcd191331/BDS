package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.LikePostDTO;

import java.util.List;

public interface ILikePostService {
	LikePostDTO saveNewLike(LikePostDTO likePostDTO);

	LikePostDTO updateLike(LikePostDTO likePostDTO);

	List<LikePostDTO> getAllLikeOfPost(Long postId);

	List<LikePostDTO> getAllLikeByUserId(Long userId);
}
