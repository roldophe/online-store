package com.onlinestore.api.product;

import com.onlinestore.api.product.web.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * This method is used to create a new category
     * resource database
     *
     * @param categoryDto is request from clients
     */
    void createNew(CategoryDto categoryDto);

    /**
     * This method is used to retrieve resources from databases
     * @return
     */
    List<CategoryDto> findAll();

    /**
     * This method is used to retrieve resources from databases by name
     * @param name of category
     * @return list of categoryDto
     */
    CategoryDto findByName(String name);
}
