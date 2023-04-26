package com.bds.thuebds.repository;

import com.bds.thuebds.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostApartmentRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> getPostEntitiesByAndAuthorId(Pageable pageable, Long id);
}
