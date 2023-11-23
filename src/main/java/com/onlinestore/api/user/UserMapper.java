package com.onlinestore.api.user;

import com.onlinestore.api.user.web.NewUserDto;
import com.onlinestore.api.user.web.UpdateUserDto;
import com.onlinestore.api.user.web.UserDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    User fromCreateUserDto(NewUserDto newUserDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateUserDto(@MappingTarget User user, UpdateUserDto newUserDto);
    @Mapping(source = "roles", target = "roles")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);

    default List<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
