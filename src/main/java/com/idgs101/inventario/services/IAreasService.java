package com.idgs101.inventario.services;

import org.springframework.http.ResponseEntity;

import com.idgs101.inventario.model.Areas;
import com.idgs101.inventario.response.AreasResponseRest;

public interface IAreasService {
	public ResponseEntity<AreasResponseRest> search();
	public ResponseEntity<AreasResponseRest> save(Areas areas);
	public ResponseEntity<AreasResponseRest> update(Areas areas, Long id);
	public ResponseEntity<AreasResponseRest> deleteById(Long id);
}
