package com.idgs101.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idgs101.inventario.dao.IAreasDao;
import com.idgs101.inventario.model.Areas;
import com.idgs101.inventario.response.AreasResponseRest;

@Service
public class AreasServiceImp implements IAreasService {
	
	@Autowired
	private IAreasDao areasDao;
	
	/*
	 * Listar todas las areas
	 * */
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<AreasResponseRest> search() {
		AreasResponseRest response = new AreasResponseRest();
		
		try {
			List<Areas> areas = (List<Areas>) areasDao.findAll();
			
			response.getAreasResponse().setAreas(areas);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta	erronea", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<AreasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AreasResponseRest>(response, HttpStatus.OK);
	}
	
	
	/*
	 * Guardar Area
	 * */
	@Override
	@Transactional
	public ResponseEntity<AreasResponseRest> save(Areas areas) {
		AreasResponseRest response = new AreasResponseRest();
		List<Areas> list = new ArrayList<>();
		
		try {
			Areas areasSaved = areasDao.save(areas);
			
			if(areasSaved != null) {
				list.add(areasSaved);
				response.getAreasResponse().setAreas(list);
				response.setMetadata("Respuesta ok", "00", "Area guardada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Area no guardada");
				return new ResponseEntity<AreasResponseRest>(response, HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta nok", "-1", "Error al guardar area");
			e.getStackTrace();
			return new ResponseEntity<AreasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AreasResponseRest>(response, HttpStatus.OK);
	}
	
	/*
	 * Actualizar Area
	 * */
	@Override
	@Transactional
	public ResponseEntity<AreasResponseRest> update(Areas areas, Long id) {
		AreasResponseRest response = new AreasResponseRest();
		List<Areas> list = new ArrayList<>();
		
		try {
			Optional<Areas> areasSearch = areasDao.findById(id);
			
			if(areasSearch != null) {
				
				areasSearch.get().setName(areas.getName());
				areasSearch.get().setDescription(areas.getDescription());
				
				Areas areasToUpdate = areasDao.save(areasSearch.get());
				
				if(areasToUpdate != null) {
					list.add(areasToUpdate);
					response.getAreasResponse().setAreas(list);
					response.setMetadata("Respuesta ok", "00", "Area actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Area no actualizada");
					return new ResponseEntity<AreasResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetadata("Respuesta nok", "-1", "Area no guardada");
				return new ResponseEntity<AreasResponseRest>(response, HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar area");
			e.getStackTrace();
			return new ResponseEntity<AreasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<AreasResponseRest>(response, HttpStatus.OK);
	}

	
	@Override
	@Transactional
	public ResponseEntity<AreasResponseRest> deleteById(Long id) {
		AreasResponseRest response = new AreasResponseRest();
		
		try {
			areasDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Registro Eliminado");
		} catch (Exception e) {
			response.setMetadata("Respuesta erronea", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<AreasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<AreasResponseRest>(response, HttpStatus.OK);
	}
}

























