package com.idgs101.inventario.dao;

import org.springframework.data.repository.CrudRepository;

import com.idgs101.inventario.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{
	
}
