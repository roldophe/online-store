package com.onlinestore.api.product.web.dto;

import jakarta.validation.constraints.*;

public record UpdateProductDto(@Size(min = 5, max = 50)
                               String name,
                               @Size(min = 5, max = 50)
                               String description,
                               @Positive(message = "Category ID must be greater than 0")
                               Integer categoryId) {
}
