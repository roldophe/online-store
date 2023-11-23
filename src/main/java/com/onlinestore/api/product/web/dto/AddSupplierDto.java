package com.onlinestore.api.product.web.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AddSupplierDto(@Nullable @NotBlank @Size(min = 5, max = 50)
                             String company,
                             LocalDate since,
                             Boolean status) {
}
