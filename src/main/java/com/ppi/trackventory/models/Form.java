package com.ppi.trackventory.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "FORMS")
@SequenceGenerator(name = "form_seq", sequenceName = "FORM_SEQ", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_seq")
	@Column(name = "ID_FORM", length = 4)
	private Integer id;

	@Column(name = "URL", length = 50)
	private String url;

	@Column(name = "NAME", length = 255)
	private String name;

	@Column(name = "ICON", length = 30)
	private String icon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@JsonBackReference
	private Form parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Form> children;

	// Getters y setters:
	public Form getParent() {
		return parent;
	}

	public void setParent(Form parent) {
		this.parent = parent;
	}

	public List<Form> getChildren() {
		return children;
	}

	public void setChildren(List<Form> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String description) {
		this.icon = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
