package com.idgs101.inventario.services;

import org.springframework.http.ResponseEntity;

import com.idgs101.inventario.model.Rols;
import com.idgs101.inventario.response.RolResponseRest;

public interface IRolsService {
	public ResponseEntity<RolResponseRest> search();
	public ResponseEntity<RolResponseRest> save(Rols rols);
	public ResponseEntity<RolResponseRest> update(Rols rols, Long id);
	public ResponseEntity<RolResponseRest> deleteById(Long id);
}
