package com.idgs101.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.idgs101.inventario.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
	
	@Query("select p from Product p where p.name like %?1%")
	List<Product> findByNameLike(String name);
	
	List<Product> findByNameContainingIgnoreCase(String name);	
}
