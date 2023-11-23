package com.onlinestore.api.product.mapper;

import com.onlinestore.api.product.model.Supplier;
import com.onlinestore.api.product.web.dto.AddSupplierDto;
import com.onlinestore.api.product.web.dto.SupplierDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier fromAddSupplierDto(AddSupplierDto addSupplierDto);
    SupplierDto toSupplierDto(Supplier supplier);
    List<SupplierDto> toSupplierDtoList(List<Supplier> suppliers);
}
