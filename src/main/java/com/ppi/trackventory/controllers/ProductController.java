package com.ppi.trackventory.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ppi.trackventory.models.Product;
import com.ppi.trackventory.services.impl.ProductService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Long categoryId) {
        try {
            return productService.getProductsByFilters(reference, name, price, categoryId);
        } catch (Exception e) {
            log.warn(e);
        }
        return new ArrayList<Product>();
    }

    // Obtener un producto por su referencia
    @GetMapping("/{reference}")
    public ResponseEntity<Product> getProductByReference(@PathVariable String reference) {
        return productService.getProductByReference(reference)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    // Actualizar un producto existente
    @SuppressWarnings("unused")
    @PutMapping("/{reference}")
    public ResponseEntity<Product> updateProduct(@PathVariable String reference, @RequestBody Product updatedProduct) {
        return productService.getProductByReference(reference)
                .map(existingProduct -> {
                    updatedProduct.setReference(reference); // Asegura que se mantiene la misma referencia
                    Product savedProduct = productService.saveProduct(updatedProduct);
                    return ResponseEntity.ok().body(savedProduct);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto por su referencia
    @SuppressWarnings("unused")
    @DeleteMapping("/{reference}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String reference) {
        return productService.getProductByReference(reference)
                .map(product -> {
                    productService.deleteProductByReference(reference);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
