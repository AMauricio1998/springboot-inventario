package com.idgs101.inventario.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rols")
public class Rols implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4512934800043193208L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "description", nullable = false)
	private String name;
	@Column(name = "name", nullable = false)
	private String description;

}
