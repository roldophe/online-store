package com.onlinestore.api.product.web;

import com.onlinestore.api.product.CategoryServiceImpl;
import com.onlinestore.api.product.web.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    @GetMapping
    public List<CategoryDto> categoryDtos (){
        return categoryService.findAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCategory(@RequestBody CategoryDto categoryDto){
        categoryService.createNew(categoryDto);
    }

    @GetMapping("/{cateName}")
    public CategoryDto findByName(@PathVariable("cateName") String name){
        return categoryService.findByName(name);
    }

}
