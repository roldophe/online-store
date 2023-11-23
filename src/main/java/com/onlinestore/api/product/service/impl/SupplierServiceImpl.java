package com.onlinestore.api.product.service.impl;

import com.onlinestore.api.product.SupplierRepository;
import com.onlinestore.api.product.mapper.SupplierMapper;
import com.onlinestore.api.product.model.Supplier;
import com.onlinestore.api.product.service.SupplierService;
import com.onlinestore.api.product.web.dto.AddSupplierDto;
import com.onlinestore.api.product.web.dto.SupplierDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public void insertSupplier(AddSupplierDto addSupplierDto) {
        Supplier supplier = supplierMapper.fromAddSupplierDto(addSupplierDto);
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplierById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product Id = %s doesn't exits in db!", id)));
        supplierRepository.delete(supplier);
    }

    @Override
    public List<SupplierDto> findAllSuppliers() {
        return supplierMapper.toSupplierDtoList(supplierRepository.findAll());
    }

    @Override
    public SupplierDto findSupplierById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product Id = %s doesn't exits in db!", id)));
        return supplierMapper.toSupplierDto(supplier);
    }
}
