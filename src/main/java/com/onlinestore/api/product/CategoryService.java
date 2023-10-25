package com.onlinestore.api.product;

import com.onlinestore.api.product.web.CategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * This method is used to create a new category
     * resource into a database
     * @param categoryDto is the request data from a client
     */
    void createNew(CategoryDto categoryDto);

    /**
     * This method is used to retrieve resource category from a database
     * @return List<CategoryDto>
     */
    List<CategoryDto> findAll();

    /**
     * This method is used to retrieve resource category by name
     * from a database
     * @param name of category
     * @return CategoryDto
     */
    CategoryDto findByName(String name);
}
