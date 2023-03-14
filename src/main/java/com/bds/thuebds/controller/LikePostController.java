package com.bds.thuebds.controller;

import com.bds.thuebds.dto.LikePostDTO;
import com.bds.thuebds.service.iService.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
public class LikePostController {
	@Autowired
	ILikePostService likePostService;

	@PostMapping("/new")
	public LikePostDTO saveNewLike(@RequestBody LikePostDTO likePostDTO) {
		return likePostService.saveNewLike(likePostDTO);
	}

	@PutMapping("/update")
	public LikePostDTO updateLike(@RequestBody LikePostDTO likePostDTO) {
		return likePostService.updateLike(likePostDTO);
	}

	@GetMapping("/list/{postId}")
	public List<LikePostDTO> getAllLikeOfPost(@PathVariable(name = "postId") Long postId) {
		return likePostService.getAllLikeOfPost(postId);
	}
}
