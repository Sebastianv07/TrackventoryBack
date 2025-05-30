package com.ppi.trackventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.ProductVariation;
import com.ppi.trackventory.services.impl.ProductVariationService;

@RestController
@RequestMapping("/product-variations")
@CrossOrigin("*")
public class ProductVariationController {

    @Autowired
    private ProductVariationService productVariationService;

    // Obtener todas las variaciones de productos
    @GetMapping
    public List<ProductVariation> getAllProductVariations() {
        return productVariationService.getAllProductVariations();
    }

    // Obtener una variación de producto por código
    @GetMapping("/{code}")
    public ResponseEntity<ProductVariation> getProductVariationByCode(@PathVariable String code) {
        Optional<ProductVariation> productVariation = productVariationService.getProductVariationByCode(code);
        if (productVariation.isPresent()) {
            return ResponseEntity.ok(productVariation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener todas las variaciones de un producto específico por su referencia
    @GetMapping("/product/{reference}")
    public List<ProductVariation> getVariationsByProductReference(@PathVariable String reference) {
        List<ProductVariation> variations = null;
        try {
            variations = productVariationService.getVariationsByProductReference(reference);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return variations;
    }

    // Crear una nueva variación de producto
    @PostMapping
    public ProductVariation createProductVariation(@RequestBody ProductVariation productVariation) {
        return productVariationService.createProductVariation(productVariation);
    }

    // Actualizar una variación de producto existente
    @PutMapping("/{code}")
    public ResponseEntity<ProductVariation> updateProductVariation(
            @PathVariable String code,
            @RequestBody ProductVariation productVariationDetails) {

        Optional<ProductVariation> updatedProductVariation = productVariationService.updateProductVariation(code,
                productVariationDetails);

        if (updatedProductVariation.isPresent()) {
            return ResponseEntity.ok(updatedProductVariation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una variación de producto
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProductVariation(@PathVariable String code) {
        boolean isDeleted = productVariationService.deleteProductVariation(code);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
