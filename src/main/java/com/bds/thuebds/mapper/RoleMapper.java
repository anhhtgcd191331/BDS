package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.RoleDTO;
import com.bds.thuebds.entity.RoleEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleMapper {
	RoleDTO entityToDto(RoleEntity roleEntity);
}
