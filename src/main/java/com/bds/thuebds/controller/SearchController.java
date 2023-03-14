package com.bds.thuebds.controller;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.dto.SearchDTO;
import com.bds.thuebds.service.iService.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
	@Autowired
	ISearchService searchService;

	@GetMapping("/filters")
	public List<PostDTO> searchPost(@RequestBody SearchDTO searchDTO) {
		return searchService.searchPost(searchDTO);
	}
}
