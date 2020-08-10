package com.pizzafinder.service.controller;

import com.pizzafinder.service.api.DownloadApi;
import com.pizzafinder.service.service.PizzaFinderService;
import com.pizzafinder.service.view.ExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import java.util.Map;


@RestController
public class DownloadController implements DownloadApi {

    @Autowired
    private PizzaFinderService pizzaService;

    @Override
    public ModelAndView excelExport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        model.put("pizzas", pizzaService.getAllPizza());
        response.setHeader("Content-Disposition", "attachment; filename=PizzaData.xls");
        return new ModelAndView(new ExcelView(), model);
    }
}
