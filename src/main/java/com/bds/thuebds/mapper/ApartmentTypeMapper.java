package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.ApartmentTypeDTO;
import com.bds.thuebds.entity.ApartmentTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApartmentTypeMapper {
    ApartmentTypeDTO entityToDto(ApartmentTypeEntity entity);

    ApartmentTypeEntity dtoToEntity(ApartmentTypeDTO apartmentTypeDTO);

    ApartmentTypeEntity updateApartmentType(@MappingTarget ApartmentTypeEntity oldApartmentType, ApartmentTypeEntity newApartmentType);
}
