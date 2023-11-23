package com.onlinestore.api.product.service.impl;

import com.onlinestore.api.product.model.Category;
import com.onlinestore.api.product.mapper.CategoryMapper;
import com.onlinestore.api.product.CategoryRepository;
import com.onlinestore.api.product.service.CategoryService;
import com.onlinestore.api.product.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void createNew(CategoryDto categoryDto) {
        Category category = categoryMapper.fromCategoryDto(categoryDto);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDtoList(categories);
    }

    @Override
    public CategoryDto findByName(String name) {
        Category category = categoryRepository.findByName(name).
                orElseThrow();
        return categoryMapper.toCategoryDto(category);
    }
}
