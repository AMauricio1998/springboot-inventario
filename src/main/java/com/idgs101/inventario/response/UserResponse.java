package com.idgs101.inventario.response;

import java.util.List;

import com.idgs101.inventario.model.Users;

import lombok.Data;

@Data
public class UserResponse {
	List<Users> users;
}
