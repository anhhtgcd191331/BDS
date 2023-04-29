package com.bds.thuebds.controller;

import com.bds.thuebds.dto.ApartmentTypeDTO;
import com.bds.thuebds.service.iService.ITypeOfApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartment-type")
public class ApartmentTypeController {

	@Autowired
	ITypeOfApartmentService service;

	@GetMapping("/list")
	List<ApartmentTypeDTO> getAll() {
		return service.getAllApartmentType();
	}

	@GetMapping("/{id}")
	ApartmentTypeDTO getApartmentTypeById(@PathVariable Long id) {
		return service.getApartmentTypeById(id);
	}

	@PostMapping("/new")
	ApartmentTypeDTO newApartmentType(@RequestBody ApartmentTypeDTO apartmentTypeDTO) {
		return service.newApartmentType(apartmentTypeDTO);
	}

	@PutMapping("update")
	ApartmentTypeDTO updateApartmentType(@RequestBody ApartmentTypeDTO apartmentTypeDTO) {
		return service.updateApartmentType(apartmentTypeDTO);
	}
}
