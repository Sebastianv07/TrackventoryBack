package com.ppi.trackventory.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppi.trackventory.models.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE " +
           "(:reference IS NULL OR p.reference = :reference) AND " +
           "(:name IS NULL OR p.name = :name) AND " +
           "(:price IS NULL OR p.price = :price) AND " +
           "(:categoryId IS NULL OR p.category.id = :categoryId)")
    List<Product> findByFilters(@Param("reference") String reference,
                                @Param("name") String name,
                                @Param("price") BigDecimal price,
                                @Param("categoryId") Long categoryId);
}
