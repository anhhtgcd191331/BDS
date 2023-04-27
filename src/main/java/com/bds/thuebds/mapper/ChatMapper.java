package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.ChatMessageDTO;
import com.bds.thuebds.entity.ChatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatMessageDTO entityToDto(ChatEntity chatEntity);
}
