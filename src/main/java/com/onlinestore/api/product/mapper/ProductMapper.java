package com.onlinestore.api.product.mapper;

import com.onlinestore.api.product.model.Product;
import com.onlinestore.api.product.web.dto.AddProductDto;
import com.onlinestore.api.product.web.dto.UpdateProductDto;
import com.onlinestore.api.product.web.dto.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "categoryId",target = "category.id")
    @Mapping(source = "supplierId",target = "supplier.id")
    Product fromCreateProductDto(AddProductDto addProductDto);

    void fromUpdateProductDto(@MappingTarget Product product, UpdateProductDto updateProductDto);
    @Mapping(source = "category.name",target = "category")
    @Mapping(source = "supplier.company",target = "company")
    ProductDto toProductDto(Product product);
    List<ProductDto> toProductDtoList(List<Product> products);
}
