package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.dto.SearchDTO;

import java.util.List;

public interface ISearchService {
	List<PostDTO> searchPost(SearchDTO searchDTO);
}
