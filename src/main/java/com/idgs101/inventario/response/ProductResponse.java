package com.idgs101.inventario.response;

import java.util.List;

import com.idgs101.inventario.model.Product;

import lombok.Data;

@Data
public class ProductResponse {
	List<Product> products;
}
