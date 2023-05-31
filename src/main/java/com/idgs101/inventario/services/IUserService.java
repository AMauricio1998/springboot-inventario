package com.idgs101.inventario.services;

import org.springframework.http.ResponseEntity;

import com.idgs101.inventario.model.Users;
import com.idgs101.inventario.response.UserResponseRest;

public interface IUserService {

	public ResponseEntity<UserResponseRest> save(Users users, Long rolsId, Long areasId); 
	public ResponseEntity<UserResponseRest> searchById(Long id);
	public ResponseEntity<UserResponseRest> searchByName(String name);
	public ResponseEntity<UserResponseRest> deleteById(Long id);
	public ResponseEntity<UserResponseRest> search();
	public ResponseEntity<UserResponseRest> update(Users users, Long rolsId, Long areasId, Long id);
}
