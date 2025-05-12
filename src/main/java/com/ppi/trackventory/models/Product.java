package com.ppi.trackventory.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @Column(name = "REFERENCE", length = 50)
    private String reference;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PRICE", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    private ProductCategory category;


	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
}