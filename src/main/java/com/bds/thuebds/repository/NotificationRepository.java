package com.bds.thuebds.repository;

import com.bds.thuebds.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
	List<NotificationEntity> getNotificationEntitiesByReceiverId(Long receiverId);
}
