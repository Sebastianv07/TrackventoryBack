package com.ppi.trackventory.models;

import javax.persistence.*;

@Entity
@Table(name = "STOCK")
public class Stock {

    @EmbeddedId
    private StockId id;

    @Column(name = "QUANTITY")
    private int quantity;

    public Stock() {
    }

    public Stock(StockId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
