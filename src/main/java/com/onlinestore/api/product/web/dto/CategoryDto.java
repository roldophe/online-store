package com.onlinestore.api.product.web.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDto(@Nullable @NotBlank @Size(min = 5, max = 50)
                          String name,
                          @Nullable @NotBlank @Size(min = 5, max = 200)
                          String description) {
}
