package com.idgs101.inventario.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idgs101.inventario.dao.IAreasDao;
import com.idgs101.inventario.dao.IRolsDao;
import com.idgs101.inventario.dao.IUserDao;
import com.idgs101.inventario.model.Areas;
import com.idgs101.inventario.model.Rols;
import com.idgs101.inventario.model.Users;
import com.idgs101.inventario.response.UserResponseRest;

@Service
public class UserServiceImp implements IUserService {
	
	private IRolsDao rolsDao;
	private IAreasDao areasDao;
	private IUserDao usersDao;
	
	public UserServiceImp(IRolsDao rolsDao, IAreasDao areasDao, IUserDao usersDao) {
		super();
		this.areasDao = areasDao;
		this.rolsDao = rolsDao;
		this.usersDao = usersDao;
	}
	
	@Override
	@Transactional
	public ResponseEntity<UserResponseRest> save(Users users, Long rolsId, Long areasId) {
		
		UserResponseRest response = new UserResponseRest();
		List<Users> list = new ArrayList<>();
		
		try {
			//Buscar la categoria asociada al producto
			Optional<Areas> areas = areasDao.findById(areasId);
			
			if(areas.isPresent()) {
				users.setAreas(areas.get());
			} else {
				response.setMetadata("respuesta erronea", "-1", "Area del usuario no encontrada");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Optional<Rols> rols = rolsDao.findById(rolsId);
			
			if(rols.isPresent()) {
				users.setRols(rols.get());
			} else {
				response.setMetadata("respuesta erronea", "-1", "Rol del usuario no encontrado");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//Guardar El usuario
			Users usersSave = usersDao.save(users);
			
			if(usersSave != null) {
				list.add(usersSave);
				response.getUser().setUsers(list);
				response.setMetadata("respuesta ok", "00", "Usuario guardado");
			} else {
				response.setMetadata("respuesta nok", "-1", "Usuario no guardado ");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al guardar usuario");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = true) 
	public ResponseEntity<UserResponseRest> searchById(Long id) {
		UserResponseRest response = new UserResponseRest();
		List<Users> list = new ArrayList<>();
		
		try {
			Optional<Users> users = usersDao.findById(id);
			
			if( users.isPresent()) {
				list.add(users.get());
				response.getUser().setUsers(list);
				response.setMetadata("Respuesta ok", "00", "Usuario encontrado");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no encontrado");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<UserResponseRest> searchByName(String name) { 
		UserResponseRest response = new UserResponseRest();
		List<Users> list = new ArrayList<>();
		
		try {
			list = usersDao.findByNameContainingIgnoreCase(name);
			
			if (list.size() > 0) {
				response.getUser().setUsers(list);
				response.setMetadata("Respuesta ok", "00", "Usuarios encontrados");
			} else {
				response.setMetadata("Respuesta ok", "-1", "Usuarios no encontrados");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			response.setMetadata("Respuesta erronea", "-1", "Error al buscar productos");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<UserResponseRest> deleteById(Long id) {
		UserResponseRest response = new UserResponseRest();
		
		try {
			usersDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Usuario eliminado");
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			response.setMetadata("respuesta erronea", "-1", "Error al eliminar usuario");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional (readOnly = true)
	public ResponseEntity<UserResponseRest> search() {
		UserResponseRest response = new UserResponseRest();
		List<Users> list = new ArrayList<>();
		
		try {
			list = (List<Users>) usersDao.findAll();
		
			if (list.size() > 0) {
				response.getUser().setUsers(list);
				response.setMetadata("Respuesta ok", "00", "Usuarios encontrados");
			} else {
				response.setMetadata("Respuesta ok", "-1", "Usuarios no encontrados");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al buscar usuarios");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<UserResponseRest> update(Users users, Long rolsId, Long areasId, Long id) {
		
		UserResponseRest response = new UserResponseRest();
		List<Users> list = new ArrayList<>();
		
		try {
			//Buscar la categoria asociada al producto
			Optional<Areas> areas = areasDao.findById(areasId);
			
			if(areas.isPresent()) {
				users.setAreas(areas.get());
			} else {
				response.setMetadata("respuesta erronea", "-1", "Area del usuario no encontrada");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Optional<Rols> rols = rolsDao.findById(rolsId);
			
			if(rols.isPresent()) {
				users.setRols(rols.get());
			} else {
				response.setMetadata("respuesta erronea", "-1", "Rol del usuario no encontrado");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			Optional<Users> userSearch = usersDao.findById(id);
			
			if (userSearch.isPresent()) {
				//Se actualiza el usuario
				userSearch.get().setName(users.getName());
				userSearch.get().setLast_name(users.getLast_name());
				userSearch.get().setPhone(users.getPhone());
				userSearch.get().setEmail(users.getEmail());
				userSearch.get().setPassword(users.getPassword());
				userSearch.get().setActive(true);
				
				//Guardar El usuario
				Users usersUpdate = usersDao.save(userSearch.get());
				
				if(usersUpdate != null) {
					list.add(usersUpdate);
					response.getUser().setUsers(list);
					response.setMetadata("respuesta ok", "00", "Usuario actualizado");
				} else {
					response.setMetadata("respuesta nok", "-1", "Ocurrio un error al actualizar");
					return new ResponseEntity<UserResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMetadata("respuesta nok", "-1", "Producto no actualizado ");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al actualizar usuario");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	/*
	 * END
	 * */
}




























