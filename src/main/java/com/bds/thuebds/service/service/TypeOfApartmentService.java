package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.ApartmentTypeDTO;
import com.bds.thuebds.entity.ApartmentTypeEntity;
import com.bds.thuebds.mapper.ApartmentTypeMapper;
import com.bds.thuebds.repository.ApartmentTypeRepository;
import com.bds.thuebds.service.iService.ITypeOfApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
@Transactional
public class TypeOfApartmentService implements ITypeOfApartmentService {
    @Autowired
    ApartmentTypeMapper mapper;

    @Autowired
    ApartmentTypeRepository repository;

    @Override
    public ApartmentTypeDTO newApartmentType(ApartmentTypeDTO apartmentTypeDTO) {
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(apartmentTypeDTO)));
    }

    @Override
    public ApartmentTypeDTO updateApartmentType(ApartmentTypeDTO apartmentTypeDTO) {
        ApartmentTypeEntity oldApartmentType = repository.getById(apartmentTypeDTO.getId());
        return mapper.entityToDto(mapper.updateApartmentType(oldApartmentType, mapper.dtoToEntity(apartmentTypeDTO)));
    }

    @Override
    public List<ApartmentTypeDTO> getAllApartmentType() {
        return repository.findAll()
                .stream()
                .map(apartmentTypeEntity -> mapper.entityToDto(apartmentTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ApartmentTypeDTO getApartmentTypeById(Long apartmentTypeId) {
        return mapper.entityToDto(repository.findById(apartmentTypeId).orElseThrow(EntityNotFoundException::new));
    }
}
