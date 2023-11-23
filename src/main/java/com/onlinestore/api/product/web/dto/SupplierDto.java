package com.onlinestore.api.product.web.dto;

import java.time.LocalDate;

public record SupplierDto(Integer id,
                          String company,
                          LocalDate since,
                          Boolean status) {
}
