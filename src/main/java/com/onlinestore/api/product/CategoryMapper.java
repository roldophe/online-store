package com.onlinestore.api.product;

import com.onlinestore.api.product.web.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryDto(CategoryDto categoryDto); //convert dto na muy tv entity
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtoList(List<Category> categories);

}
