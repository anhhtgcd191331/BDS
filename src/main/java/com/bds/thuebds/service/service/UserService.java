package com.bds.thuebds.service.service;

import com.bds.thuebds.constant.ErrorMessageConstants;
import com.bds.thuebds.customClassException.UserDisableException;
import com.bds.thuebds.customClassException.UserDuplicatedUserNameException;
import com.bds.thuebds.dto.UserDTO;
import com.bds.thuebds.entity.UserEntity;
import com.bds.thuebds.entity.UserRoleEntity;
import com.bds.thuebds.mapper.UserMapper;
import com.bds.thuebds.repository.UserRepository;
import com.bds.thuebds.repository.UserRoleRepository;
import com.bds.thuebds.service.iService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO saveNewUser(UserDTO userDTO) throws UserDuplicatedUserNameException {
		if (checkDuplicatedUserByUserNameForSaveNew(userDTO).equalsIgnoreCase("OK")
			&& userDTO.getUserId() == null) {
			UserEntity userEntity = userRepository.save(userMapper.dtoToEntity(userDTO));
			userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			userRoleRepository.saveAll(userEntity.getUserRoleEntityList());
			return userMapper.entityToDto(userEntity);
		}
		throw new UserDuplicatedUserNameException();
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		UserEntity oldUser = userRepository.getUserEntityByUsername(userDTO.getUsername());
		UserEntity newUser = userMapper.dtoToEntity(userDTO);
		newUser.setId(oldUser.getId());
		UserEntity updatedUser = userMapper.updateUserEntity(oldUser, newUser);
		for (UserRoleEntity userRoleEntity : userRoleRepository.getUserRoleEntitiesByUserId(oldUser.getId())) {
			userRoleRepository.deleteUserRoleEntityById(userRoleEntity.getId());
		}
		userRoleRepository.saveAll(updatedUser.getUserRoleEntityList());
		return userMapper.entityToDto(updatedUser);
	}

	@Override
	public List<UserDTO> getUserList(Pageable pageable) {
		return userRepository.getUserList(pageable)
			.stream()
			.map(userEntity -> userMapper.entityToDto(userEntity))
			.collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getUsersByUsername(String username) {
		return userRepository.getUserEntitiesByUsernameContains(username)
				.stream().map(userEntity -> userMapper.entityToDto(userEntity))
				.collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long userId) {
		return userMapper.entityToDto(userRepository.getUserEntityByUserId(userId));
	}

	private String checkDuplicatedUserByUserNameForSaveNew(UserDTO userDTO) {
		UserEntity userExist = userRepository.getUserEntityByUsername(userDTO.getUsername());
		if (userExist == null) {
			return "OK";
		} else {
			return ErrorMessageConstants.USERNAME_EXISTS;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.getUserEntityByUsername(username);
		if (userEntity != null && userEntity.isEnable()) {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			userEntity.getUserRoleEntityList().forEach(userRoleEntity -> {
				authorities.add(new SimpleGrantedAuthority(userRoleEntity.getRole().getRoleName()));
			});
			return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorities);
		}
		if (userEntity == null) {
			throw new UsernameNotFoundException("Can not find userName:" + username + " in the database");
		}
		if (!userEntity.isEnable()) {
			throw new UserDisableException();
		} else {
			throw new RuntimeException();
		}
	}
}
