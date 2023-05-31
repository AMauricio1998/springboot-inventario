package com.idgs101.inventario.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idgs101.inventario.model.Users;
import com.idgs101.inventario.response.UserResponseRest;
import com.idgs101.inventario.services.IUserService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	private IUserService userService;
	
	public UsuarioController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserResponseRest> save(
			@RequestParam("name") String name,
			@RequestParam("last_name") String last_name,
			@RequestParam("phone") int phone,
			@RequestParam("email") String email,
			@RequestParam("active") Boolean active,
			@RequestParam("password") String password,
			@RequestParam("rolsId") Long rolsID,
			@RequestParam("areasId") Long areasID
			) throws IOException {
		Users users = new Users();
		users.setName(name);
		users.setLast_name(last_name);
		users.setPhone(phone);
		users.setEmail(email);
		users.setActive(true);
		users.setPassword(password);
		
		ResponseEntity<UserResponseRest> response = userService.save(users, rolsID, areasID);
		
		return response;
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponseRest> searchById(@PathVariable Long id) {
		ResponseEntity<UserResponseRest> response = userService.searchById(id);
		return response;
	}
	
	@GetMapping("/users/filter/{name}")
	public ResponseEntity<UserResponseRest> searchByName(@PathVariable String name){
		ResponseEntity<UserResponseRest> response = userService.searchByName(name);
		return response;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserResponseRest> deleteById(@PathVariable Long id) {
		ResponseEntity<UserResponseRest> response = userService.deleteById(id);
		return response;
	}
	
	@GetMapping("/users")
	public ResponseEntity<UserResponseRest> search() {
		ResponseEntity<UserResponseRest> response = userService.search();
		return response;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserResponseRest> update (
			@RequestParam("name") String name,
			@RequestParam("last_name") String last_name,
			@RequestParam("phone") int phone,
			@RequestParam("email") String email,
			@RequestParam("active") Boolean active,
			@RequestParam("password") String password,
			@RequestParam("rolsId") Long rolsID,
			@RequestParam("areasId") Long areasID,
			@PathVariable Long id) throws IOException {
		
		Users users = new Users();
		users.setName(name);
		users.setLast_name(last_name);
		users.setPhone(phone);
		users.setEmail(email);
		users.setActive(true);
		users.setPassword(password);
		
		ResponseEntity<UserResponseRest> response = userService.update(users, rolsID, areasID, id);
		
		return response;
		
	}
	/*
	 * END
	 * */
}













