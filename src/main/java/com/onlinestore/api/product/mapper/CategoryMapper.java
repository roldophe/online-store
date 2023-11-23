package com.onlinestore.api.product.mapper;

import com.onlinestore.api.product.model.Category;
import com.onlinestore.api.product.web.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryDto(CategoryDto categoryDto); //convert dto na muy tv entity
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtoList(List<Category> categories);

}
