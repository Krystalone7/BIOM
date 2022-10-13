package com.biom.service.mapper;

import com.biom.dto.UserDto;
import com.biom.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User model);
}
