package com.onlinestore.api.auth.web;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(@NotBlank
                              String refreshToken) {
}
