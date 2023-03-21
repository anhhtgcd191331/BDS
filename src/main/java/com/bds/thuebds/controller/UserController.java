package com.bds.thuebds.controller;

import com.bds.thuebds.dto.UserDTO;
import com.bds.thuebds.service.iService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/new")
	public UserDTO saveNewUser(@RequestBody UserDTO userDTO) {
		return userService.saveNewUser(userDTO);
	}

	@GetMapping("/list")
	public List<UserDTO> listUser(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
	                              @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return userService.getUserList(pageable);
	}

	@GetMapping("/find/{username}")
	public UserDTO getUserByUsername(@PathVariable(name = "username") String username) {
		return userService.getUserByUsername(username);
	}

	@PutMapping("/update")
	public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		return userService.updateUser(userDTO);
	}
	@GetMapping("/id/{userId}")
	public UserDTO getUserById(@PathVariable(name = "userId") Long userId) {
		return userService.getUserById(userId);
	}
}
