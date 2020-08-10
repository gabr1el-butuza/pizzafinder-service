package com.pizzafinder.service.view;

import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {


        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=PizzaData.xls");

        @SuppressWarnings("unchecked")
        List<Pizza> pizzas = (List<Pizza>) model.get("pizzas");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Pizza Details");
        sheet.setDefaultColumnWidth(40);


        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true); //Set wordwrap
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);



        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Description");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Spice Level");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Vegetarian");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Vegan");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("LactoVegetarian");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("OvoVegetarian");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Locations");
        header.getCell(7).setCellStyle(style);



        int rowCount = 1;



        for (Pizza pizza : pizzas) {
            Row pizzaRow = sheet.createRow(rowCount++);
            pizzaRow.createCell(0).setCellValue(pizza.getName());
            pizzaRow.createCell(1).setCellValue(pizza.getDescription());
            pizzaRow.createCell(2).setCellValue(String.valueOf(pizza.getSpiceLevel()));
            pizzaRow.createCell(3).setCellValue(pizza.getIsVegetarian());
            pizzaRow.createCell(4).setCellValue(pizza.getIsVegan());
            pizzaRow.createCell(5).setCellValue(pizza.getIsLactoVegetarian());
            pizzaRow.createCell(6).setCellValue(pizza.getIsOvoVegetarian());
            StringBuilder sb = new StringBuilder();
            for(Location loc: pizza.getLocations()){
                sb.append(loc.getPlaceName());
                sb.append("\n");
            }
            if(sb.length() > 35) {
                CellStyle rowStyle = workbook.createCellStyle();
                rowStyle.setWrapText(true);
                pizzaRow.setRowStyle(rowStyle);
                pizzaRow.createCell(7).setCellValue(sb.toString());
                pizzaRow.getCell(7).setCellStyle(rowStyle);
            } else {
                pizzaRow.createCell(7).setCellValue(sb.toString());
            }

        }
    }
}
