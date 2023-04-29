package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.UserDTO;
import com.bds.thuebds.entity.UserEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
	@Mappings({
			@Mapping(target = "roleList", ignore = true),
			@Mapping(target = "userId", source = "id"),
			@Mapping(target = "password", ignore = true)
	})
	UserDTO entityToDto(UserEntity userEntity);

	@Mappings({
		@Mapping(target = "userRoleEntityList", ignore = true),
		@Mapping(target = "id", ignore = true)
	})
	UserEntity dtoToEntity(UserDTO userDTO);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "username", ignore = true),
			@Mapping(target = "email", ignore = true),
			@Mapping(target = "password", ignore = true)
	})
	UserEntity updateUserEntity(@MappingTarget UserEntity oldUser, UserEntity newUser);
}
