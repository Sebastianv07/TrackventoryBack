package com.ppi.trackventory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PRODUCT_VARIATIONS")
public class ProductVariation {

    @ManyToOne
    @JoinColumn(name = "PRODUCT_VRT", referencedColumnName = "REFERENCE")
    private Product productVrt;
    
    @Id
    @Column(name = "CODE")
    private String code;

    @ManyToOne
    @JoinColumn(name = "COLOR_VRT", referencedColumnName = "ID_COLOR")
    private Color colorVrt;
    

	public Product getProductVrt() {
		return productVrt;
	}

	public void setProductVrt(Product product_vrt) {
		this.productVrt = product_vrt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Color getColorVrt() {
		return colorVrt;
	}

	public void setColorVrt(Color color_vrt) {
		this.colorVrt = color_vrt;
	}

}
