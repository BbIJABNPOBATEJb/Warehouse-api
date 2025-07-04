package com.warehouse.api.service;

import com.warehouse.api.dto.ProductDto;
import com.warehouse.api.entity.Product;
import com.warehouse.api.mapper.ProductMapper;
import com.warehouse.api.repository.ProductRepository;
import com.warehouse.api.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        // Arrange
        ProductDto inputDto = new ProductDto(null, "Laptop", "Powerful", new BigDecimal("999.99"), 5);
        Product entity = Product.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .description("Powerful")
                .price(new BigDecimal("999.99"))
                .quantity(5)
                .build();

        when(mapper.toEntity(inputDto)).thenReturn(entity);
        when(repository.save(any(Product.class))).thenReturn(entity);
        when(mapper.toDto(any(Product.class))).thenReturn(inputDto);

        // Act
        ProductDto result = productService.create(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(repository, times(1)).save(any());
    }
}