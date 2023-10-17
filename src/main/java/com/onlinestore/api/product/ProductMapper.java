package com.onlinestore.api.product;

import com.onlinestore.api.product.web.CreateProductDto;
import com.onlinestore.api.product.web.UpdateProductDto;
import com.onlinestore.api.product.web.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId",target = "category.id")
    Product fromCreateProductDto(CreateProductDto createProductDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProductDto(@MappingTarget Product product, UpdateProductDto updateProductDto);
    @Mapping(source = "category.name",target = "category")
    ProductDto toProductDto(Product product);
    List<ProductDto> toProductDtoList(List<Product> products);
}
