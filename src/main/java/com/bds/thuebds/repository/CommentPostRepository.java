package com.bds.thuebds.repository;

import com.bds.thuebds.entity.CommentPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentPostRepository extends JpaRepository<CommentPostEntity, Long> {
	List<CommentPostEntity> getCommentPostEntitiesByPostId(Long postId);
}
