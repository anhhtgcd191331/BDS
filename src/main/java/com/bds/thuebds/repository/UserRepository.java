package com.bds.thuebds.repository;

import com.bds.thuebds.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query(value = "SELECT * FROM appuser " +
			" WHERE id =:userId", nativeQuery = true)
	UserEntity getUserEntityByUserId(@Param("userId") Long userId);

	@Query(value = "SELECT * FROM appuser", nativeQuery = true)
	List<UserEntity> getUserList(Pageable pageable);

	UserEntity getUserEntityByUsername(String username);

	List<UserEntity> getUserEntitiesByUsernameContains(String username);
}
