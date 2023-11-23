package com.onlinestore.api.product.service.impl;

import com.onlinestore.api.product.model.Category;
import com.onlinestore.api.product.model.Product;
import com.onlinestore.api.product.mapper.ProductMapper;
import com.onlinestore.api.product.ProductRepository;
import com.onlinestore.api.product.service.ProductService;
import com.onlinestore.api.product.web.dto.AddProductDto;
import com.onlinestore.api.product.web.dto.UpdateProductDto;
import com.onlinestore.api.product.web.dto.ProductDto;
import com.onlinestore.utils.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public void createNew(AddProductDto addProductDto) {
        Product product = productMapper.fromCreateProductDto(addProductDto);
        product.setUuid(UUID.randomUUID().toString());
        product.setCode("PRO-" + RandomUtil.generateCode());
        System.out.println(product);
        productRepository.save(product);
    }

    @Override
    public void updateByUuid(String uuid, UpdateProductDto updateProductDto) {
        //step1: check uuid of product in the database
        if (productRepository.existsByUuid(uuid)) {
            Product product = productRepository.findByUuid(uuid).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Product UUID = %s doesn't exits in db!", uuid)));
            productMapper.fromUpdateProductDto(product, updateProductDto);

            if (updateProductDto.categoryId() != null) {
                Category category = new Category();
                category.setId(updateProductDto.categoryId());
                product.setCategory(category);
            }
            productRepository.save(product);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Product UUID=%s doesn't exits in db!", uuid));
    }

    @Override
    public void deleteByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product UUID = %s doesn't exits in db!", uuid))
        );
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return productMapper.toProductDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto findByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Product UUID = %s doesn't exits in db!", uuid))
        );
        return productMapper.toProductDto(product);
    }
}
