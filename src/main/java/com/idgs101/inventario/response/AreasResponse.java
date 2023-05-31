package com.idgs101.inventario.response;

import java.util.List;

import com.idgs101.inventario.model.Areas;

import lombok.Data;

@Data
public class AreasResponse {
	private List<Areas> areas;
}
