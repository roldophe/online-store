package com.onlinestore.api.user.web;

import jakarta.validation.constraints.NotBlank;

public record IsDeletedDto(@NotBlank
                           Boolean isDeleted) {
}
