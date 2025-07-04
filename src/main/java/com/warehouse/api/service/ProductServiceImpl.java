package com.warehouse.api.service;

import com.warehouse.api.dto.ProductDto;
import com.warehouse.api.entity.Product;
import com.warehouse.api.mapper.ProductMapper;
import com.warehouse.api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = mapper.toEntity(dto);
        product.setId(UUID.randomUUID());
        return mapper.toDto(repository.save(product));
    }

    @Override
    public List<ProductDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    @Override
    public ProductDto update(UUID id, ProductDto dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setQuantity(dto.getQuantity());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}