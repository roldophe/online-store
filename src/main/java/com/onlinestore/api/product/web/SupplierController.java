package com.onlinestore.api.product.web;

import com.onlinestore.api.product.service.SupplierService;
import com.onlinestore.api.product.web.dto.AddSupplierDto;
import com.onlinestore.api.product.web.dto.SupplierDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public List<SupplierDto> getAllSuppliers() {
        return supplierService.findAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierDto getSupplierById(@PathVariable Integer id) {
        return supplierService.findSupplierById(id);
    }

    @PostMapping
    private void AddNewSupplier(@RequestBody @Valid AddSupplierDto addSupplierDto) {
        supplierService.insertSupplier(addSupplierDto);
    }

    @DeleteMapping("/{id}")
    private void deleteNewSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplierById(id);
    }
}
