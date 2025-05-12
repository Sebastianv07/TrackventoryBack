package com.ppi.trackventory.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppi.trackventory.models.Product;
import com.ppi.trackventory.models.ProductVariation;
import com.ppi.trackventory.repositories.ProductVariationRepository;

@Service
public class ProductVariationService {

    @Autowired
    private ProductVariationRepository productVariationRepository;
    
    @Autowired
    private ProductService productService;

    // Obtener todas las variaciones de productos
    public List<ProductVariation> getAllProductVariations() {
        return productVariationRepository.findAll(); 
    }

    // Obtener una variación de producto por código
    public Optional<ProductVariation> getProductVariationByCode(String code) {
        return productVariationRepository.findById(code);
    }

    // Obtener todas las variaciones de un producto por su referencia
    public List<ProductVariation> getVariationsByProductReference(String reference) throws Exception {
        Optional<Product> optionalProduct = productService.getProductByReference(reference);
        
        if (optionalProduct.isPresent()) {
            return productVariationRepository.findByProductVrt(optionalProduct.get());
        } else {
            throw new Exception("Product not found with reference: " + reference);
        }
    }

    // Crear una nueva variación de producto
    public ProductVariation createProductVariation(ProductVariation productVariation) {
        return productVariationRepository.save(productVariation);
    }

    // Actualizar una variación de producto existente
    public Optional<ProductVariation> updateProductVariation(String code, ProductVariation productVariationDetails) {
        return productVariationRepository.findById(code).map(productVariation -> {
            productVariation.setProductVrt(productVariationDetails.getProductVrt());
            productVariation.setCode(productVariationDetails.getCode());
            productVariation.setColorVrt(productVariationDetails.getColorVrt());
            return productVariationRepository.save(productVariation);
        });
    }

    // Eliminar una variación de producto
    public boolean deleteProductVariation(String id) {
        return productVariationRepository.findById(id).map(productVariation -> {
            productVariationRepository.delete(productVariation);
            return true;
        }).orElse(false);
    }

    // Método agregado: Obtener una variación por código para StockService
    public Optional<ProductVariation> getVariationByCode(String variationCode) {
        return productVariationRepository.findById(variationCode);
    }
}
