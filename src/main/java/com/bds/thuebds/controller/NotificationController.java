package com.bds.thuebds.controller;

import com.bds.thuebds.dto.NotificationDTO;
import com.bds.thuebds.service.iService.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

	@Autowired
	INotificationService notificationService;

	@GetMapping("/list")
	List<NotificationDTO> getAllNotification() {
		return notificationService.getAllNotification();
	}

	@GetMapping("/receiver")
	List<NotificationDTO> getAllNotificationByReceiverId(@RequestParam(name = "receiverId") Long receiverId) {
		return notificationService.getNotificationByReceiverId(receiverId);
	}

	@PutMapping("/update")
	NotificationDTO updateNotification(@RequestBody NotificationDTO notificationDTO) {
		return notificationService.updateNotification(notificationDTO);
	}

}
