package com.ppi.trackventory.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String description;

	public ProductCategory(){
		
	}

	public ProductCategory(Long id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}

    // @Column(name = "NAME", length = 20)
    // private String name;

    // @Column(name = "DESCRIPTION", length = 50)
    // private String description;
    
	// public String getCode() {
	// 	return code;
	// }

	// public void setCode(String code) {
	// 	this.code = code;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
