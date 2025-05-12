package com.ppi.trackventory.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class StockId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "STORE_STK", referencedColumnName = "ID")
    private Store storeStk;

    @ManyToOne
    @JoinColumn(name = "VARIATION_STK", referencedColumnName = "CODE")
    private ProductVariation variationStk;

    public StockId() {
    }

    public StockId(Store storeStk, ProductVariation variationStk) {
        this.storeStk = storeStk;
        this.variationStk = variationStk;
    }

    public Store getStoreStk() {
        return storeStk;
    }

    public void setStoreStk(Store storeStk) {
        this.storeStk = storeStk;
    }

    public ProductVariation getVariationStk() {
        return variationStk;
    }

    public void setVariationStk(ProductVariation variationStk) {
        this.variationStk = variationStk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return Objects.equals(storeStk, stockId.storeStk) &&
               Objects.equals(variationStk, stockId.variationStk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeStk, variationStk);
    }
}
