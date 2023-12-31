package com.onlinestore.api.product.service;

import com.onlinestore.api.product.web.dto.UpdateProductDto;
import com.onlinestore.api.product.web.dto.AddProductDto;
import com.onlinestore.api.product.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    /**
     * This method is used to create a new product resource into a database
     *
     * @param addProductDto is request data from a client
     */
    void createNew(AddProductDto addProductDto);

    /**
     * This method is used to update a product resource in the database based on its UUID.
     *
     * @param uuid             the UUID of the product to be updated
     * @param updateProductDto the data containing the updated product information
     */
    void updateByUuid(String uuid, UpdateProductDto updateProductDto);

    /**
     * This method is used to delete a product resource in the database based on its UUID.
     *
     * @param uuid the UUID of the product to be deleted
     */
    void deleteByUuid(String uuid);

    /**
     * Retrieves a list of all products.
     *
     * @return a list of ProductDto objects representing the products
     */
    List<ProductDto> findAll();

    /**
     * This method is used to retrieve resource product by name
     *
     * @param uuid the UUID of the product to be retrieved
     * @return a ProductDto object representing the product
     */
    ProductDto findByUuid(String uuid);

}
