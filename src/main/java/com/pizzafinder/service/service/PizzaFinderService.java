package com.pizzafinder.service.service;

import com.pizzafinder.service.domain.LocationEntity;
import com.pizzafinder.service.domain.PizzaEntity;
import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import com.pizzafinder.service.dto.SpiceLevel;
import com.pizzafinder.service.repository.LocationRepository;
import com.pizzafinder.service.repository.PizzaFinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.pizzafinder.service.domain.LocationEntity.toLocation;
import static com.pizzafinder.service.domain.PizzaEntity.toPizza;
import static com.pizzafinder.service.dto.Location.toLocationEntity;

@Service
public class PizzaFinderService {

    @Autowired
    protected PizzaFinderRepository pizzaFinderRepository;

    @Autowired
    protected LocationRepository locationRepository;

    public Pizza createPizza(Pizza pizza) {
        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setName(pizza.getName());
        pizzaEntity.setDescription(pizza.getDescription());
        pizzaEntity.setSpiceLevel(pizza.getSpiceLevel());
        pizzaEntity.setPicPath(pizza.getPicPath());
        pizzaEntity.setIsVegetarian(pizza.getIsVegetarian());
        pizzaEntity.setIsLactoVegetarian(pizza.getIsLactoVegetarian());
        pizzaEntity.setIsOvoVegetarian(pizza.getIsOvoVegetarian());
        pizzaEntity.setIsVegan(pizza.getIsVegan());

        updatePizzaLocations(pizzaEntity.getLocationList(), pizza.getLocations());

        PizzaEntity savedPizza = pizzaFinderRepository.saveAndFlush(pizzaEntity);

        return toPizza(savedPizza);
    }

    public List<Pizza> findPizzasByName(String name) {
        List<PizzaEntity> pizzaEntities = pizzaFinderRepository.findByNameIgnoreCaseContaining(name);
        List<Pizza> pizzas = new ArrayList<>();

        for (PizzaEntity i : pizzaEntities) {
            pizzas.add(toPizza(i));
        }

        return pizzas;
    }

    public List<Pizza> getAllPizza() {
        List<PizzaEntity> pizzass = pizzaFinderRepository.findAll();
        List<Pizza> items = new ArrayList<>();


        for (PizzaEntity i : pizzass) {
            items.add(toPizza(i));
        }
        return items;
    }

    public Pizza getPizzaById(String id) {
        PizzaEntity pizzaById = pizzaFinderRepository.getOne(id);
        return toPizza(pizzaById);
    }


    public Pizza updatePizzaDetails(String id, Pizza pizzaDetails) {
        PizzaEntity pizza = pizzaFinderRepository.getOne(id);
                if(pizza==null){
                    throw new RuntimeException("Pizza with id could not be found.");
                }
        pizza.setName(pizzaDetails.getName());
        pizza.setDescription(pizzaDetails.getDescription());
        pizza.setSpiceLevel(pizzaDetails.getSpiceLevel());
        pizza.setPicPath(pizzaDetails.getPicPath());
        pizza.setIsLactoVegetarian(pizzaDetails.getIsLactoVegetarian());
        pizza.setIsOvoVegetarian(pizzaDetails.getIsOvoVegetarian());
        pizza.setIsVegan(pizzaDetails.getIsVegan());
        pizza.setIsVegetarian(pizzaDetails.getIsVegetarian());

        updatePizzaLocations(pizza.getLocationList(), pizzaDetails.getLocations());

        PizzaEntity updatedPizza = pizzaFinderRepository.save(pizza);
        return toPizza(updatedPizza);
    }

    private void updatePizzaLocations(List<LocationEntity> locationList, List<Location> locations) {

        //build a map<googleId, LocationEntity>
        Map<String, LocationEntity> locationEntitiesByGoogleId = new HashMap<String, LocationEntity>();
        //build a map <googleId, Location>
        Map<String, Location> locationsByGoogleId = new HashMap<String, Location>();

            for(LocationEntity i : locationList){
                locationEntitiesByGoogleId.put(i.getGoogleId(), i);
            }

            for(Location j : locations){
                locationsByGoogleId.put(j.getGoogleId(), j);
            }

        //remove deleted locations
        for (String locationEntityGoogleId : locationEntitiesByGoogleId.keySet()) {
            if (!locationsByGoogleId.containsKey(locationEntityGoogleId)) {
                LocationEntity locEntity = locationEntitiesByGoogleId.get(locationEntityGoogleId);
                locationList.remove(locEntity);
            }
        }
        //add new locations
        for (String locationByGoogleId : locationsByGoogleId.keySet()) {
            if (!locationEntitiesByGoogleId.containsKey(locationByGoogleId)) {
                Location loc = locationsByGoogleId.get(locationByGoogleId);
                LocationEntity locEntity = locationRepository.findByGoogleId(locationByGoogleId);
                if(locEntity != null){
                    locationList.add(locEntity);
                } else {
                    locationList.add(toLocationEntity(loc));
                }
            }
        }
    }

    public void removePizza(String id){
        pizzaFinderRepository.deleteById(id);
    }

    public List<SpiceLevel> getAllSpiceLevel(){
        return Arrays.asList(SpiceLevel.values());
    }


    public Location getLocationById(String id) {
        LocationEntity locatie = locationRepository.getOne(id);
        return toLocation(locatie);
    }

}
