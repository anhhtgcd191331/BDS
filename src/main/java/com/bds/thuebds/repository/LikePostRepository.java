package com.bds.thuebds.repository;

import com.bds.thuebds.entity.LikePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LikePostRepository extends JpaRepository<LikePostEntity, Long> {
	List<LikePostEntity> getLikePostEntitiesByPostId(Long postId);

	List<LikePostEntity> getLikePostEntitiesByUserId(Long userId);
}
