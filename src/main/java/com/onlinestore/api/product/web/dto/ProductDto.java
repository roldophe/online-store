package com.onlinestore.api.product.web.dto;

public record ProductDto(String uuid,
                         String code,
                         String name,
                         String description,
                         String image,
                         String category,
                         String company) {
}
