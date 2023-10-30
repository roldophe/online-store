package com.onlinestore.api.user;

import com.onlinestore.api.user.web.NewUserDto;
import com.onlinestore.api.user.web.UpdateUserDto;
import com.onlinestore.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createNewUser(NewUserDto newUserDto) {
        //Check username if exits
        if (userRepository.existsByUsernameAndIsDeletedFalse(newUserDto.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exits..!");
        }
        //Check email if exist
        if (userRepository.existsByEmailAndIsDeletedFalse(newUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exits..!");
        }
        boolean isNotFound = newUserDto.roleIds().stream()
                .anyMatch(roleId -> !roleRepository.existsById(roleId));
        if (isNotFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role ID doesn't exits in db!");
        }

        Set<Role> roles = newUserDto.roleIds().stream()
                .map(roleId -> Role.builder().id(roleId).build())
                .collect(Collectors.toSet());
        User user = userMapper.fromCreateUserDto(newUserDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(newUserDto.password()));
        user.setIsVerified(false);
        user.setIsDeleted(false);
        user.setRoles(roles);
        userRepository.save(user);

    }

    @Override
    public void updateByUuid(String uuid, UpdateUserDto updateUserDto) {

        //step1: check email of user in the database
        if (userRepository.existsByEmailAndIsDeletedFalse(updateUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exits..!");
        }
        //step2: check uuid of user in the database
        User foundUser = userRepository.selectUserByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID = %s doesn't exits in db!", uuid)));

        userMapper.fromUpdateUserDto(foundUser, updateUserDto);
        userRepository.save(foundUser);
    }

    @Override
    public UserDto findByUuid(String uuid) {
        User foundUser = userRepository.selectUserByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User UUID = %s doesn't exits in db!", uuid)));
        return userMapper.toUserDto(foundUser);
    }

    @Override
    public void deleteByUuid(String uuid) {
        User foundUser = userRepository.selectUserByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID = %s doesn't exits in db!", uuid)));
        userRepository.delete(foundUser);
    }

    @Override
    @Transactional
    public void updateIsDeletedByUuid(String uuid, Boolean isDeleted) {
        boolean isFound = userRepository.checkUserByUuid(uuid);

        if (isFound) {
            userRepository.updateIsDeletedStatusByUuid(uuid, isDeleted);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User UUID = %s doesn't exits in db!", uuid));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtoList(users);
    }
}
