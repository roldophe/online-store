package com.onlinestore.api.auth.web;

import com.onlinestore.api.auth.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(@Valid @RequestBody RegisterDto registerDto) throws MessagingException {
        authService.register(registerDto);
        return Map.of("message", "Please check email and verified...!");
    }

    @PostMapping("/verify")
    public Map<String, String> verifiedCode(@RequestBody VerifyDto verifyDto) {
        authService.verify(verifyDto);
        return Map.of("message","Congratulation! Email has been verified..!");
    }
}
