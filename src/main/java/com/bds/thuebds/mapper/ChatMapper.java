package com.bds.thuebds.mapper;

import com.bds.thuebds.dto.ChatDTO;
import com.bds.thuebds.entity.ChatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatDTO entityToDto(ChatEntity chatEntity);
}
