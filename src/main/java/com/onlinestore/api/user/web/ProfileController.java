package com.onlinestore.api.user.web;

import com.onlinestore.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@RestController
public class ProfileController {
    private final UserService userService;

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        return userService.me(authentication);
    }
}
