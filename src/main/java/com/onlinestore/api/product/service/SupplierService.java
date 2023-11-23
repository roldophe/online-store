package com.onlinestore.api.product.service;

import com.onlinestore.api.product.web.dto.AddSupplierDto;
import com.onlinestore.api.product.web.dto.SupplierDto;

import java.util.List;

public interface SupplierService {
    void insertSupplier(AddSupplierDto addSupplierDto);
    void deleteSupplierById(Integer id);
    List<SupplierDto> findAllSuppliers();
    SupplierDto findSupplierById(Integer id);
}
