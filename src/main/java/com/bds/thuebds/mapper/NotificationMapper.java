package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.NotificationDTO;
import com.bds.thuebds.entity.NotificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	NotificationDTO entityToDto(NotificationEntity notificationEntity);
	NotificationEntity dtoToEntity(NotificationDTO notificationDTO);
}
