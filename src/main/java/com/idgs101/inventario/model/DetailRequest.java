package com.idgs101.inventario.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "detail_requests")
public class DetailRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3891832511249644303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "amount", nullable = false)
	private int amount;
	@Column(name = "created_at", nullable = false)
	private Date created_at;
	@Column(name = "updated_at", nullable = false)
	private Date updated_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_id", referencedColumnName = "id")
    private Requests request;
}















