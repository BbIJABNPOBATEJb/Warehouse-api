package com.warehouse.api.service;

import com.warehouse.api.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto create(ProductDto dto);

    List<ProductDto> getAll();

    ProductDto getById(UUID id);

    ProductDto update(UUID id, ProductDto dto);

    void delete(UUID id);
}
