package com.bds.thuebds.repository;

import com.bds.thuebds.entity.RoleEntity;
import com.bds.thuebds.entity.UserEntity;
import com.bds.thuebds.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
	@Query(value = "delete from user_role where id =:userRoleEntityId", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteUserRoleEntityById(@Param("userRoleEntityId") Long userRoleEntityId);

	List<UserRoleEntity> getUserRoleEntitiesByUserId(Long userId);
}
