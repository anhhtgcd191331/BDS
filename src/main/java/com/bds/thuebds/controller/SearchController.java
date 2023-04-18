package com.bds.thuebds.controller;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.service.iService.IDatabaseSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

	@Autowired
	IDatabaseSearch iDatabaseSearch;

	@GetMapping("/filters")
	public List<PostDTO> searchPost(@RequestBody LinkedHashMap<String, Object> map) throws SQLException {
		return iDatabaseSearch.search(map);
	}

}
