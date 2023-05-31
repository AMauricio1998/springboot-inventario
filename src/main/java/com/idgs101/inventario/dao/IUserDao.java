package com.idgs101.inventario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.idgs101.inventario.model.Users;

public interface IUserDao extends CrudRepository<Users, Long>{
	@Query("select u from Users u where u.name like %?1%")
	List<Users> findByNameLike(String name);
	
	List<Users> findByNameContainingIgnoreCase(String name);
}
