package com.bds.thuebds.controller;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.service.iService.IDatabaseSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

	@Autowired
	IDatabaseSearch iDatabaseSearch;

	@PostMapping("/filters")
	public List<PostDTO> searchPost(@RequestBody LinkedHashMap<String, Object> map) throws SQLException {
		return iDatabaseSearch.search(map);
	}

}
