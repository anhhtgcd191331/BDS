package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.NotificationDTO;
import com.bds.thuebds.mapper.NotificationMapper;
import com.bds.thuebds.repository.NotificationRepository;
import com.bds.thuebds.service.iService.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService implements INotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	NotificationMapper mapper;

	@Override
	public List<NotificationDTO> getNotificationByReceiverId(Long receiverId) {
		return notificationRepository.getNotificationEntitiesByReceiverId(receiverId)
			.stream()
			.map(notificationEntity -> mapper.entityToDto(notificationEntity))
			.filter(notificationDTO -> !notificationDTO.isRead())
			.sorted(Comparator.comparing(NotificationDTO::getNotificationId).reversed())
			.collect(Collectors.toList());
	}

	@Override
	public List<NotificationDTO> getAllNotification() {
		return notificationRepository
				.findAll().stream()
				.map(notificationEntity -> mapper.entityToDto(notificationEntity))
				.collect(Collectors.toList());
	}
}
