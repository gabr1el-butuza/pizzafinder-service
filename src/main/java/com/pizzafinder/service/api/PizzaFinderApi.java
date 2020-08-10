package com.pizzafinder.service.api;

import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import com.pizzafinder.service.dto.SpiceLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface PizzaFinderApi {

    @RequestMapping(value = "/pizza",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Pizza> createPizza(@RequestBody Pizza body);

    @RequestMapping(value = "/pizza/search/{name}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Pizza>> findPizzasByName(@PathVariable("name") String name);

    @RequestMapping(value = "/pizzas",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Pizza>> getAllPizza();

    @RequestMapping(value = "/pizza/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Pizza> getPizzaById(@PathVariable("id") String id);

    @RequestMapping(value = "/pizza-delete/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Boolean> removePizza(@PathVariable("id") String id);

    @RequestMapping(value = "/pizza/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Pizza> updatePizzaDetails(@PathVariable("id") String id, @RequestBody Pizza body);

    @RequestMapping(value = "/allSpice",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<SpiceLevel>> getAllSpiceLevel();

    @RequestMapping(value = "/location/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Location> getLocationById(@PathVariable("id") String id);

}

