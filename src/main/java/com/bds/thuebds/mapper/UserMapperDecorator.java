package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.RoleDTO;
import com.bds.thuebds.dto.UserDTO;
import com.bds.thuebds.entity.RoleEntity;
import com.bds.thuebds.entity.UserEntity;
import com.bds.thuebds.entity.UserRoleEntity;
import com.bds.thuebds.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class UserMapperDecorator implements UserMapper {
	@Autowired
	@Qualifier("delegate")
	private UserMapper delegate;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleMapper roleMapper;

	@Override
	public UserEntity dtoToEntity(UserDTO userDTO) {
		UserEntity userEntity = delegate.dtoToEntity(userDTO);
		List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
		for (int i = 0; i < userDTO.getRoleList().size(); i++) {
			RoleEntity roleEntity = roleRepository.getRoleEntityByRoleCode(userDTO.getRoleList().get(i).getRoleCode());
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setUser(userEntity);
			userRoleEntity.setRole(roleEntity);
			userRoleEntityList.add(userRoleEntity);
		}
		if (userDTO.getPassword() != null) {
			userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		}
		userEntity.setUserRoleEntityList(userRoleEntityList);
		return userEntity;
	}

	@Override
	public UserDTO entityToDto(UserEntity userEntity) {
		UserDTO userDTO = delegate.entityToDto(userEntity);
		List<RoleDTO> roleDTOList = userEntity.getUserRoleEntityList().stream()
			.map(UserRoleEntity::getRole)
			.map(roleEntity -> roleMapper.entityToDto(roleEntity))
			.collect(Collectors.toList());
		userDTO.setRoleList(roleDTOList);
		return userDTO;
	}

	@Override
	public UserEntity updateUserEntity(UserEntity oldUser, UserEntity newUserEntity) {
		UserEntity updatedUserEntity = delegate.updateUserEntity(oldUser, newUserEntity);
		updatedUserEntity.setUserRoleEntityList(newUserEntity.getUserRoleEntityList());
		return updatedUserEntity;
	}
}
