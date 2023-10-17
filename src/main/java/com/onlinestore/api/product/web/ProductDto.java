package com.onlinestore.api.product.web;

public record ProductDto (String uuid,
                          String name,
                          String code,
                          String description,
                          String image,
                          String category){
}
