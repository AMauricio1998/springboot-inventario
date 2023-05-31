package com.idgs101.inventario.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7906578927466420741L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private int price;
	@Column(name = "account", nullable = false)
	private int account;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "product_code", nullable = false)
	private String product_code;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "picture", nullable = true)
	private byte[] picture;
}












