package com.onlinestore.api.user.web;

import com.onlinestore.api.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createNewUser(@RequestBody @Valid NewUserDto newUserDto) {
        userService.createNewUser(newUserDto);
    }
    @GetMapping()
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{uuid}")
    public UserDto findUserByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {
        userService.deleteByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable String uuid,
                                      @RequestBody IsDeletedDto isDeletedDto) {
        userService.updateIsDeletedByUuid(uuid, isDeletedDto.isDeleted());
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    public void updateUserByUuid(@PathVariable String uuid,
                                 @RequestBody UpdateUserDto updateUserDto) {
        userService.updateByUuid(uuid, updateUserDto);
    }

}
