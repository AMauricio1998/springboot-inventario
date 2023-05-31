package com.idgs101.inventario.services;

import org.springframework.http.ResponseEntity;

import com.idgs101.inventario.model.Category;
import com.idgs101.inventario.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category vategory);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
