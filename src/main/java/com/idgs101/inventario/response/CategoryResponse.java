package com.idgs101.inventario.response;

import java.util.List;

import com.idgs101.inventario.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;
	
}
