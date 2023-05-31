package com.idgs101.inventario.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseRest extends ResponseRest{
	private UserResponse user = new UserResponse();
}
