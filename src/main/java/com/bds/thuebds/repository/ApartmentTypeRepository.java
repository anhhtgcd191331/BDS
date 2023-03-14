package com.bds.thuebds.repository;

import com.bds.thuebds.entity.ApartmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApartmentTypeRepository extends JpaRepository<ApartmentTypeEntity, Long> {
	ApartmentTypeEntity getApartmentTypeEntityByTypeOfApartment(String typeOfApartment);
}
