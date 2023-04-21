package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.NotificationDTO;
import com.bds.thuebds.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	@Mappings(
			@Mapping(target = "notificationId", source = "id")
	)
	NotificationDTO entityToDto(NotificationEntity notificationEntity);

	@Mappings(
			@Mapping(target = "notificationId", source = "id")
	)
	NotificationEntity dtoToEntity(NotificationDTO notificationDTO);

	NotificationEntity updateNotification(@MappingTarget NotificationEntity oldNotification,
										  NotificationEntity newNotification);
}
