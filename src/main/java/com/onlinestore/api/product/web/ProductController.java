package com.onlinestore.api.product.web;

import com.onlinestore.api.product.ProductService;
import com.onlinestore.api.product.web.UpdateProductDto;
import com.onlinestore.api.product.web.CreateProductDto;
import com.onlinestore.api.product.web.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    public void updateProductByUuid(@PathVariable String uuid,
                                    @RequestBody UpdateProductDto updateProductDto){
        productService.updateByUuid(uuid,updateProductDto);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNew(@RequestBody @Valid CreateProductDto createProductDto){
        ///System.out.println("Product:" +createProductDto);
        productService.createNew(createProductDto);
    }
    @GetMapping
    public List<ProductDto> findAll(){
        return productService.findAll();
    }
    @DeleteMapping("{uuid}")
    public void deleteByUuid(@PathVariable String uuid){
        productService.deleteByUuid(uuid);
    }
    @GetMapping("/{uuid}")
    public ProductDto findByUuid(@PathVariable String uuid){
        return productService.findByUuid(uuid);
    }

}
