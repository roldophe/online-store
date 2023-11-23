package com.onlinestore.api.auth;

import com.onlinestore.api.auth.web.*;

public interface AuthService {
    AuthDto refreshToken(RefreshTokenDto refreshTokenDto);

    void register(RegisterDto registerDto);

    AuthDto login(LoginDto loginDto);

    void verify(VerifyDto verifyDto);
}
