package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.ApartmentTypeDTO;

import java.util.List;

public interface ITypeOfApartmentService {
	ApartmentTypeDTO newApartmentType(ApartmentTypeDTO apartmentTypeDTO);

	ApartmentTypeDTO updateApartmentType(ApartmentTypeDTO apartmentTypeDTO);

	List<ApartmentTypeDTO> getAllApartmentType();

	ApartmentTypeDTO getApartmentTypeById(Long apartmentTypeId);
}
