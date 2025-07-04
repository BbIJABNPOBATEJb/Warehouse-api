package com.warehouse.api.mapper;

import com.warehouse.api.dto.ProductDto;
import com.warehouse.api.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto dto);

    ProductDto toDto(Product entity);
}