package com.idgs101.inventario.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1094705566652540691L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "last_name", nullable = false)
	private String last_name;
	@Column(name = "phone", nullable = false)
	private int phone;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rols rols;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Areas areas;
}












