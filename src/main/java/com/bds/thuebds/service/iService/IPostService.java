package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.PostDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPostService {
	void saveNewPost(PostDTO postDTO,
	                 List<MultipartFile> images,
	                 List<MultipartFile> videos) throws IOException;

	PostDTO updatePost(PostDTO postDTO);

	List<PostDTO> getAllPost(Pageable pageable);

	List<PostDTO> getPostByAuthorId(Pageable pageable, Long authorId);

	PostDTO getPostById(Long postId);

	Integer getTotalPost();
}
