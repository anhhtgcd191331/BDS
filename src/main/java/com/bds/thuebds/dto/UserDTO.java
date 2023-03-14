package com.bds.thuebds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	private Long userId;
	private String username;
	private String password;
	private String fullName;
	private String gender;
	private String email;
	private String address;
	private boolean isEnable;
	List<RoleDTO> roleList;
}
