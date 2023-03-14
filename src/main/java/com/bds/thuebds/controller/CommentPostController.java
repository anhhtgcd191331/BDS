package com.bds.thuebds.controller;

import com.bds.thuebds.dto.CommentPostDTO;
import com.bds.thuebds.service.iService.ICommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentPostController {
	@Autowired
	ICommentPostService commentPostService;

	@PostMapping("/new")
	public CommentPostDTO saveNewCommentPost(@RequestBody CommentPostDTO commentPostDTO) {
		return commentPostService.saveNewComment(commentPostDTO);
	}

	@PutMapping("/update")
	public CommentPostDTO updateCommentPost(@RequestBody CommentPostDTO commentPostDTO) {
		return commentPostService.updateComment(commentPostDTO);
	}

	@DeleteMapping("/delete")
	public void deleteCommentPost(@RequestBody CommentPostDTO commentPostDTO) {
		commentPostService.deleteComment(commentPostDTO);
	}

	@GetMapping("/list/{postId}")
	public List<CommentPostDTO> getAllCommentOfPost(@PathVariable(name = "postId") Long postId) {
		return commentPostService.getAllCommentOfPost(postId);
	}
}
