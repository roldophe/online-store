package com.onlinestore.api.product.web.dto;

import jakarta.validation.constraints.*;

public record AddProductDto(@NotBlank(message = "Name is required!")
                               @Size(min = 5, max = 50)
                               String name,
                            @NotBlank(message = "Description is required!")
                               @Size(min = 5, message = "Description must be greater than 5 digit")
                               String description,
                            @NotBlank
                               String image,
                            @NotNull(message = "Category ID is required!")
                               @Positive(message = "Category ID must be greater than 0")
                               Integer categoryId,
                            @NotNull(message = "SupplierId ID is required!")
                               @Positive(message = "SupplierId ID must be greater than 0")
                               Integer supplierId) {
}
