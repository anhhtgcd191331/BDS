package com.bds.thuebds.repository;

import com.bds.thuebds.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity getRoleEntityByRoleCode(String roleCode);
}
