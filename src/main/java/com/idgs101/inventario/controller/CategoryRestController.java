package com.idgs101.inventario.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idgs101.inventario.model.Category;
import com.idgs101.inventario.response.CategoryResponseRest;
import com.idgs101.inventario.services.ICategoryService;
import com.idgs101.inventario.util.CategoryExcelReporter;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	/*
	 *Obtener todas las categoria 
	*/
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
	}
	
	/*
	 *Obtener categoria por id
	*/
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
	}
	
	/*
	 *Crear categoria 
	*/
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
		
		ResponseEntity<CategoryResponseRest> response = service.save(category);
		return response;
	}
	
	/*
	 *Actualizar categoria 
	*/
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.update(category, id);
		return response;
	}
	
	/*
	 *Eliminar categoria 
	*/
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
		
		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
		return response;
	}
	
	@GetMapping("/categories/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_category";
		response.setHeader(headerKey, headerValue);
		
		ResponseEntity<CategoryResponseRest> categoryResponse = service.search();
		
		CategoryExcelReporter excelExporter = new CategoryExcelReporter(categoryResponse.getBody().getCategoryResponse().getCategory());
		excelExporter.export(response);
	}
}













