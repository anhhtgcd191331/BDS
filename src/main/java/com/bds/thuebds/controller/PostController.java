package com.bds.thuebds.controller;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.service.iService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

	@Autowired
	IPostService postService;

	@Value("${server.compression.mime-types}")
	private List<String> contentVideos;

	@GetMapping("/list")
	public List<PostDTO> listPost(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
	                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return postService.getAllPost(pageable);
	}

	@GetMapping("/total")
	public Integer totalPost() {
		return postService.getTotalPost();
	}

	@GetMapping("/{postId}")
	public PostDTO findPostById(@PathVariable(name = "postId") Long postId) {
		return postService.getPostById(postId);
	}

	@PostMapping(value = "/new", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public HttpStatus saveNewPost(@RequestPart PostDTO postDTO,
	                              @RequestPart(required = false, name = "images") @Valid List<MultipartFile> images,
	                              @RequestPart(required = false, name = "video") @Valid List<MultipartFile> videos) throws IOException {
		if (videos.size() != 0) {
			for (MultipartFile video : videos) {
				String contentType = video.getContentType();
				if (!contentVideos.contains(contentType)) {
					return HttpStatus.BAD_REQUEST;
				}
			}
		}
		postService.saveNewPost(postDTO, images, videos);
		return HttpStatus.OK;
	}

	@PutMapping("/update")
	public PostDTO updatePost(@RequestBody PostDTO postDTO) {
		return postService.updatePost(postDTO);
	}

}
