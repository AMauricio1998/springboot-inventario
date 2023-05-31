package com.idgs101.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idgs101.inventario.dao.IRolsDao;
import com.idgs101.inventario.model.Rols;
import com.idgs101.inventario.response.RolResponseRest;

@Service
public class RolsServiceImp implements IRolsService {
	
	@Autowired
	private IRolsDao rolsDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RolResponseRest> search() {
		RolResponseRest response = new RolResponseRest();
		
		try {
			List<Rols> rols = (List<Rols>) rolsDao.findAll();
			
			response.getRolResponse().setRols(rols);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta	erronea", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<RolResponseRest> save(Rols rols) {
		RolResponseRest response = new RolResponseRest();
		List<Rols> list = new ArrayList<>();
		
		try {
			Rols rolsSaved = rolsDao.save(rols);
			
			if(rolsSaved != null) {
				list.add(rolsSaved);
				response.getRolResponse().setRols(list);
				response.setMetadata("Respuesta ok", "00", "Rol guardado");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Rol no guardado");
				return new ResponseEntity<RolResponseRest>(response, HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Respuesta nok", "-1", "Error al guardar el rol");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	/*
	 * Actualizar Rol
	 * */
	@Override
	@Transactional
	public ResponseEntity<RolResponseRest> update(Rols rols, Long id) {
		RolResponseRest response = new RolResponseRest();
		List<Rols> list = new ArrayList<>();
		
		try {
			Optional<Rols> rolsSearch = rolsDao.findById(id);
			
			if(rolsSearch != null) {
				
				rolsSearch.get().setName(rols.getName());
				rolsSearch.get().setDescription(rols.getDescription());
				
				Rols rolsToUpdate = rolsDao.save(rolsSearch.get());
				
				if(rolsToUpdate != null) {
					list.add(rolsToUpdate);
					response.getRolResponse().setRols(list);
					response.setMetadata("Respuesta ok", "00", "Rol actualizado");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Rol no actualizado");
					return new ResponseEntity<RolResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetadata("Ocurrio un error", "-1", "Rol no guardado");
				return new ResponseEntity<RolResponseRest>(response, HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMetadata("Ocurrio un error", "-1", "Error al actualizar el rol");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<RolResponseRest> deleteById(Long id) {
		RolResponseRest response = new RolResponseRest();
		
		try {
			rolsDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Registro Eliminado");
		} catch (Exception e) {
			response.setMetadata("Respuesta erronea", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}
	
}
