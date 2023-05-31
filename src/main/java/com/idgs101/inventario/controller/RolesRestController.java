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

import com.idgs101.inventario.model.Rols;
import com.idgs101.inventario.response.RolResponseRest;
import com.idgs101.inventario.services.IRolsService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class RolesRestController {
	
	@Autowired
	private IRolsService service;
	
	
	@GetMapping("/rols")
	public ResponseEntity<RolResponseRest> searchRols() {
		ResponseEntity<RolResponseRest> response = service.search();
		return response;
	}
	
	@PostMapping("/rols")
	public ResponseEntity<RolResponseRest> save(@RequestBody Rols rols) {
		ResponseEntity<RolResponseRest> response = service.save(rols);
		return response;
	}
	
	@PutMapping("/rols/{id}")
	public ResponseEntity<RolResponseRest> update(@RequestBody Rols rols, @PathVariable Long id) {
		ResponseEntity<RolResponseRest> response = service.update(rols, id);
		return response;
	}
	
	@DeleteMapping("/rols/{id}")
	public ResponseEntity<RolResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<RolResponseRest> response = service.deleteById(id);
		return response;
	}

}




















