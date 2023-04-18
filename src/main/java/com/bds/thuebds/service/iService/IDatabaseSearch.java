package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.PostDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public interface IDatabaseSearch {
	public List<PostDTO> search(LinkedHashMap<String, Object> map) throws SQLException;
}
