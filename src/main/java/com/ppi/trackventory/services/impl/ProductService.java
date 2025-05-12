package com.ppi.trackventory.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppi.trackventory.models.Product;
import com.ppi.trackventory.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    private String validateStringField(String field) {
        return Optional.ofNullable(field)
                       .filter(f -> !f.trim().isEmpty())
                       .orElse(null);
    }

    private BigDecimal validateBigDecimalField(BigDecimal field) {
        return Optional.ofNullable(field)
                       .filter(f -> f.compareTo(BigDecimal.ZERO) != 0)
                       .orElse(null);
    }

    private Long validateLongField(Long field) {
        return Optional.ofNullable(field)
                       .filter(f -> f != 0L)
                       .orElse(null);
    }

    public List<Product> getProductsByFilters(String reference, String name, BigDecimal price, Long categoryId) {
        reference = validateStringField(reference);
        name = validateStringField(name);
        price = validateBigDecimalField(price);
        categoryId = validateLongField(categoryId);

        return productRepository.findByFilters(reference, name, price, categoryId);
    }

    public Optional<Product> getProductByReference(String reference) {
        return productRepository.findById(reference);
    }

    public void deleteProductByReference(String reference) {
        productRepository.deleteById(reference);
    }
}

