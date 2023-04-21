package com.bds.thuebds.service.iService;

import com.bds.thuebds.dto.NotificationDTO;

import java.util.List;

public interface INotificationService {
	List<NotificationDTO> getNotificationByReceiverId(Long receiverId);

	List<NotificationDTO> getAllNotification();

	NotificationDTO updateNotification(NotificationDTO notificationDTO);
}
