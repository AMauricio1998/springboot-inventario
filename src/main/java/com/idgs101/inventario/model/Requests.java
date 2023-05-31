package com.idgs101.inventario.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "requests")
public class Requests implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1249846071757969646L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "code", nullable = false)
	private String code;
	@Column(name = "created_at", nullable = false)
	private Date created_at;
	@Column(name = "updated_at", nullable = false)
	private Date updated_at;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Users users;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Estatus estatus;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "requests", cascade = CascadeType.ALL)
	//public List<DetailRequest> detailRequest;
}





















