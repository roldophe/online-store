package com.onlinestore.api.auth;

import com.onlinestore.api.auth.web.LoginDto;
import com.onlinestore.api.auth.web.RegisterDto;
import com.onlinestore.api.auth.web.VerifyDto;
import jakarta.mail.MessagingException;

public interface AuthService {
    void register(RegisterDto registerDto) throws MessagingException;
    void login(LoginDto loginDto);
    void verify(VerifyDto verifyDto);
}
