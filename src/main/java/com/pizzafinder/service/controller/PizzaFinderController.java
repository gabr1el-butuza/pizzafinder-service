package com.pizzafinder.service.controller;

import com.pizzafinder.service.api.PizzaFinderApi;
import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import com.pizzafinder.service.dto.SpiceLevel;
import com.pizzafinder.service.service.PizzaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PizzaFinderController implements PizzaFinderApi {


    @Autowired
    private PizzaFinderService pizzaService;

    @Override
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza body) {
        Pizza pizza = pizzaService.createPizza(body);
        return new ResponseEntity<>(pizza, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<Pizza>> findPizzasByName(@PathVariable("name") String name) {
        List<Pizza> pizzas = pizzaService.findPizzasByName(name);
        return new ResponseEntity<>(pizzas, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") String id) {
        Pizza pizzaById = pizzaService.getPizzaById(id);
        return new ResponseEntity<>(pizzaById, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<Pizza>> getAllPizza() {
        List<Pizza> allpiz = pizzaService.getAllPizza();
        return new ResponseEntity<>(allpiz, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pizza> updatePizzaDetails(@PathVariable("id") String id, @RequestBody Pizza body) {
        Pizza updatedPizza = pizzaService.updatePizzaDetails(id,body);
        return new ResponseEntity<>(updatedPizza, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> removePizza(@PathVariable("id") String id){
         pizzaService.removePizza(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SpiceLevel>> getAllSpiceLevel() {
        List<SpiceLevel> allspice = pizzaService.getAllSpiceLevel();
        return new ResponseEntity<>(allspice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Location> getLocationById(@PathVariable("id") String id) {
        Location locationById = pizzaService.getLocationById(id);
        return new ResponseEntity<>(locationById, HttpStatus.OK);
    }

}
