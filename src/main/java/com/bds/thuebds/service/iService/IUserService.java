package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
	UserDTO saveNewUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO);
	List<UserDTO> getUserList(Pageable pageable);
	List<UserDTO> getUsersByUsername(String username);
	UserDTO getUserById(Long userId);
	Long countTotalUser();
}
