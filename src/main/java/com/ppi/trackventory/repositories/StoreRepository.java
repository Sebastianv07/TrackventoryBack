package com.ppi.trackventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppi.trackventory.models.Store;
import com.ppi.trackventory.models.DTO.StockByStoreDTO;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = """
            SELECT
                s.code AS codeStore,
                st.quantity AS stock,
                c.name AS nameColor,
                p.name AS nameProduct,
                v.code AS codeVariation,
                v.color_vrt AS colorVrt
            FROM us_ppi.stores s
            JOIN us_ppi.stock st ON s.id = st.store_stk
            JOIN us_ppi.product_variations v ON v.code = st.variation_stk
            JOIN us_ppi.products p ON p.reference = v.product_vrt
            JOIN us_ppi.colors c ON c.id_color = v.color_vrt
            ORDER BY p.reference
            """, nativeQuery = true)
    List<StockByStoreDTO> getStockByStore();

}
