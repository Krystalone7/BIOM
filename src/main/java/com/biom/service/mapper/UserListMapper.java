package com.biom.service.mapper;

import com.biom.dto.UserDto;
import com.biom.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserDto> toDtoList(List<User> users);
}
