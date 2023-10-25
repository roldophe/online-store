package com.onlinestore.api.auth;

import com.onlinestore.api.auth.web.RegisterDto;
import com.onlinestore.api.user.web.NewUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AuthMapper {
    NewUserDto mapRegisterDtoToNewUserDto(RegisterDto registerDto);
}
