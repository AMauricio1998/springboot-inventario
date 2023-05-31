package com.idgs101.inventario.controller;

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

import com.idgs101.inventario.model.Areas;
import com.idgs101.inventario.response.AreasResponseRest;
import com.idgs101.inventario.services.IAreasService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class AreasRestController {
	@Autowired
	private IAreasService service;
	
	/*
	 * Obtener todas las areas
	 * */

	@GetMapping("/areas")
	public ResponseEntity<AreasResponseRest> searchAreas() {
		ResponseEntity<AreasResponseRest> response = service.search();
		return response;
	}
	
	/*
	 * Guardar area
	 * */
	@PostMapping("/areas")
	public ResponseEntity<AreasResponseRest> save(@RequestBody Areas areas) {
		ResponseEntity<AreasResponseRest> response = service.save(areas);
		return response;
	}
	
	/*
	 * Atualizar area
	 * */
	@PutMapping("/areas/{id}")
	public ResponseEntity<AreasResponseRest> update(@RequestBody Areas areas, @PathVariable Long id) {
		ResponseEntity<AreasResponseRest> response = service.update(areas, id);
		return response;
	}
	
	@DeleteMapping("/areas/{id}")
	public ResponseEntity<AreasResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<AreasResponseRest> response = service.deleteById(id);
		return response;
	}
	
}








