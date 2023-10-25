package com.onlinestore.api.user;

import com.onlinestore.api.user.web.NewUserDto;
import com.onlinestore.api.user.web.UpdateUserDto;
import com.onlinestore.api.user.web.UserDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    User fromCreateUserDto(NewUserDto newUserDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateUserDto(@MappingTarget User user, UpdateUserDto newUserDto);
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);



}
