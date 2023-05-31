package com.idgs101.inventario.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.idgs101.inventario.model.Product;

public class ProductExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Product> product;
	
	
	public ProductExcelExporter (List<Product> products) {
		this.product = products;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Resultado");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row, 0, "ID", style);
		createCell(row, 1, "Nombre", style);
		createCell(row, 2, "Precio", style);
		createCell(row, 3, "Cantidad", style);
		createCell(row, 4, "Categoría", style);
		createCell(row, 4, "Codigo product", style);
		createCell(row, 4, "Descripcion", style);
	}
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		
		if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		
		cell.setCellStyle(style);
		
	}
	
	
	private void writeDataLines() {
		
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		
		for( Product result: product) {
			
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, String.valueOf(result.getId()), style);
			createCell(row, columnCount++, result.getName(), style);
			createCell(row, columnCount++, result.getPrice(), style);
			createCell(row, columnCount++, result.getAccount(), style);
			createCell(row, columnCount++, result.getCategory().getName(), style);
			createCell(row, columnCount++, result.getProduct_code(), style);			
			createCell(row, columnCount++, result.getDescription(), style);
		}
	}
	
	
	public void export(HttpServletResponse response) throws IOException {
		
		writeHeaderLine(); //write the header
		writeDataLines(); //write the data
		
		ServletOutputStream servletOutput = response.getOutputStream();
		workbook.write(servletOutput);
		workbook.close();
		
		servletOutput.close();
		
		
	}
}