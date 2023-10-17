package com.onlinestore.api.product;

import com.onlinestore.api.product.web.UpdateProductDto;
import com.onlinestore.api.product.web.CreateProductDto;
import com.onlinestore.api.product.web.ProductDto;

import java.util.List;

public interface ProductService {
    void createNew(CreateProductDto createProductDto);
    void updateByUuid(String uuid, UpdateProductDto updateProductDto);

    void deleteByUuid(String uuid);
    List<ProductDto> findAll();
    ProductDto findByUuid(String uuid);

}
