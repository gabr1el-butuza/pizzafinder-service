package com.pizzafinder.service.service;

import com.pizzafinder.service.domain.LocationEntity;
import com.pizzafinder.service.domain.PizzaEntity;
import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import com.pizzafinder.service.dto.SpiceLevel;
import com.pizzafinder.service.repository.LocationRepository;
import com.pizzafinder.service.repository.PizzaFinderRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.pizzafinder.service.dto.Location.toLocationEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PizzaFinderServiceTest {

    private PizzaFinderService pizzaFinderService;

    private PizzaFinderRepository mockPizzaFinderRepository;
    private LocationRepository mockLocationRepo;

    @Before
    public void setup() {
        pizzaFinderService = new PizzaFinderService();
        mockLocationRepo = mock(LocationRepository.class);
        mockPizzaFinderRepository = mock(PizzaFinderRepository.class);
        pizzaFinderService.pizzaFinderRepository = mockPizzaFinderRepository;
        pizzaFinderService.locationRepository = mockLocationRepo;
    }

    @Test
    public void testCreatePizzaWithoutExistingLocation() {
        Pizza pizza = new Pizza();
        List<Location> locations = new ArrayList<>();
        List<LocationEntity> locationlist = new ArrayList<>();
        Location loc1 = new Location();
        LocationEntity loc2 = new LocationEntity();
        locations.add(loc1);
        locationlist.add(loc2);

        pizza.setName("Test Pizza");
        pizza.setDescription("Description of Test Pizza");
        pizza.setSpiceLevel(SpiceLevel.CRAZY);
        pizza.setLocations(locations);

        PizzaEntity savedPizzaEntity = new PizzaEntity();
        savedPizzaEntity.setId("testPizza");
        savedPizzaEntity.setName(pizza.getName());
        savedPizzaEntity.setDescription(pizza.getDescription());
        savedPizzaEntity.setSpiceLevel(pizza.getSpiceLevel());
        savedPizzaEntity.setLocationList(locationlist);

        when(mockPizzaFinderRepository.saveAndFlush(any())).thenReturn(savedPizzaEntity);
        when(mockLocationRepo.findByGoogleId(any())).thenReturn(null);

        Pizza savedPizza = pizzaFinderService.createPizza(pizza);

        assertNotNull(savedPizza);
        assertEquals("testPizza", savedPizza.getId());
        assertEquals(pizza.getDescription(), savedPizza.getDescription());
        assertEquals(pizza.getName(), savedPizza.getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.getSpiceLevel());
    }

    @Test
    public void testCreatePizzaWithExistingLocation() {
        Pizza pizza = new Pizza();
        List<Location> locations = new ArrayList<>();
        List<LocationEntity> locationlist = new ArrayList<>();
        Location loc1 = new Location();
        LocationEntity loc2 = new LocationEntity();
        locations.add(loc1);
        locationlist.add(loc2);

        pizza.setName("Test Pizza");
        pizza.setDescription("Description of Test Pizza");
        pizza.setSpiceLevel(SpiceLevel.CRAZY);
        pizza.setLocations(locations);

        PizzaEntity savedPizzaEntity = new PizzaEntity();
        savedPizzaEntity.setId("testPizza");
        savedPizzaEntity.setName(pizza.getName());
        savedPizzaEntity.setDescription(pizza.getDescription());
        savedPizzaEntity.setSpiceLevel(pizza.getSpiceLevel());
        savedPizzaEntity.setLocationList(locationlist);

        when(mockPizzaFinderRepository.saveAndFlush(any())).thenReturn(savedPizzaEntity);
        when(mockLocationRepo.findByGoogleId(any())).thenReturn(loc2);

        Pizza savedPizza = pizzaFinderService.createPizza(pizza);

        assertNotNull(savedPizza);
        assertEquals("testPizza", savedPizza.getId());
        assertEquals(pizza.getDescription(), savedPizza.getDescription());
        assertEquals(pizza.getName(), savedPizza.getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.getSpiceLevel());

    }


    @Test
    public void testFindPizzasByName() {
        Pizza pizza = new Pizza();

        pizza.setName("Pizza Napoli");
        pizza.setDescription("Other desc");
        pizza.setSpiceLevel(SpiceLevel.NONE);

        List<PizzaEntity> savedPizzaEntity = new ArrayList<>();

        PizzaEntity savedPizzaEntity2 = new PizzaEntity();
        savedPizzaEntity2.setId("testPizza");
        savedPizzaEntity2.setName(pizza.getName());
        savedPizzaEntity2.setDescription(pizza.getDescription());
        savedPizzaEntity2.setSpiceLevel(pizza.getSpiceLevel());
        savedPizzaEntity.add(savedPizzaEntity2);

        when(mockPizzaFinderRepository.findByNameIgnoreCaseContaining(any())).thenReturn(savedPizzaEntity);

        List<Pizza> savedPizza = pizzaFinderService.findPizzasByName(any());

        assertNotNull(savedPizza);
        assertEquals("testPizza", savedPizza.get(0).getId());
        assertEquals(pizza.getDescription(), savedPizza.get(0).getDescription());
        assertEquals(pizza.getName(), savedPizza.get(0).getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.get(0).getSpiceLevel());

    }

    @Test
    public void testGetPizzaById() {
        Pizza getByIdPizza = new Pizza();

        getByIdPizza.setName("Test getByIdPizza");
        getByIdPizza.setDescription("Description of Test getByIdPizza");
        getByIdPizza.setSpiceLevel(SpiceLevel.HOT);

        PizzaEntity savedPizzaEntity = new PizzaEntity();
        savedPizzaEntity.setId("testGetByIdPizza");
        savedPizzaEntity.setName(getByIdPizza.getName());
        savedPizzaEntity.setDescription(getByIdPizza.getDescription());
        savedPizzaEntity.setSpiceLevel(getByIdPizza.getSpiceLevel());

        when(mockPizzaFinderRepository.getOne(any())).thenReturn(savedPizzaEntity);

        Pizza savedPizza = pizzaFinderService.getPizzaById(any());

        assertNotNull(savedPizza);
        assertEquals("testGetByIdPizza", savedPizza.getId());
        assertEquals(getByIdPizza.getDescription(), savedPizza.getDescription());
        assertEquals(getByIdPizza.getName(), savedPizza.getName());
        assertEquals(getByIdPizza.getSpiceLevel(), savedPizza.getSpiceLevel());
    }

    @Test
    public void testGetAllPizza() {
        Pizza pizza = new Pizza();

        pizza.setName("Test getAllPizza");
        pizza.setDescription("Description of Test getAllPizza");
        pizza.setSpiceLevel(SpiceLevel.CRAZY);

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);


        PizzaEntity savedPizzaEntity = new PizzaEntity();
        savedPizzaEntity.setId("testgetAllPizza");
        savedPizzaEntity.setName(pizzaList.get(0).getName());
        savedPizzaEntity.setDescription(pizzaList.get(0).getDescription());
        savedPizzaEntity.setSpiceLevel(pizzaList.get(0).getSpiceLevel());

        List<PizzaEntity> pizzaEntityList = new ArrayList<>();
        pizzaEntityList.add(savedPizzaEntity);

        when(mockPizzaFinderRepository.findAll()).thenReturn(pizzaEntityList);

        List<Pizza> savedPizza = pizzaFinderService.getAllPizza();

        assertNotNull(savedPizza);
        assertEquals("testgetAllPizza", savedPizza.get(0).getId());
        assertEquals(pizza.getDescription(), savedPizza.get(0).getDescription());
        assertEquals(pizza.getName(), savedPizza.get(0).getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.get(0).getSpiceLevel());
    }

    @Test
    public void testUpdatePizzaDetails() {
        List<Location> locations = new ArrayList<Location>();
        Location location = new Location();

        location.setId("1");
        location.setAdress("Address1");
        location.setGoogleId("34");
        location.setLatitude("632234");
        location.setLongitude("124512");

        locations.add(location);

        Pizza pizza = new Pizza();

        pizza.setId("1");
        pizza.setName("Test updatePizzaDetails");
        pizza.setDescription("Description of Test updatePizzaDetails");
        pizza.setSpiceLevel(SpiceLevel.HOT);
        pizza.setLocations(locations);

        PizzaEntity savedPizzaEntity = new PizzaEntity();
        List<LocationEntity> locationEntitiess = new ArrayList<LocationEntity>();

        savedPizzaEntity.setId("1");
        savedPizzaEntity.setName(pizza.getName());
        savedPizzaEntity.setDescription(pizza.getDescription());
        savedPizzaEntity.setSpiceLevel(pizza.getSpiceLevel());
        for(Location i : locations){
            locationEntitiess.add(toLocationEntity(i));
        }
        savedPizzaEntity.setLocationList(locationEntitiess);

        when(mockPizzaFinderRepository.getOne(any())).thenReturn(savedPizzaEntity);
        when(mockPizzaFinderRepository.save(any())).thenReturn(savedPizzaEntity);

        Pizza savedPizza = pizzaFinderService.updatePizzaDetails("1", pizza);

        assertNotNull(savedPizza);
        assertEquals("1", savedPizza.getId());
        assertEquals(pizza.getDescription(), savedPizza.getDescription());
        assertEquals(pizza.getName(), savedPizza.getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.getSpiceLevel());
        assertEquals(locationEntitiess, locationEntitiess);
    }

    @Test(expected = RuntimeException.class)
    public void testUpdatePizzaDetailsWithException() {
        List<Location> locations = new ArrayList<Location>();
        Location location = new Location();

        location.setId("1");
        location.setAdress("Address1");
        location.setGoogleId("34");
        location.setLatitude("632234");
        location.setLongitude("124512");

        locations.add(location);

        Pizza pizza = new Pizza();

        pizza.setName("Test updatePizzaDetails");
        pizza.setDescription("Description of Test updatePizzaDetails");
        pizza.setSpiceLevel(SpiceLevel.HOT);
        pizza.setLocations(locations);

        PizzaEntity savedPizzaEntity = new PizzaEntity();
        List<LocationEntity> locationEntitiess = new ArrayList<LocationEntity>();

        savedPizzaEntity.setName(pizza.getName());
        savedPizzaEntity.setDescription(pizza.getDescription());
        savedPizzaEntity.setSpiceLevel(pizza.getSpiceLevel());
        for(Location i : locations){
            locationEntitiess.add(toLocationEntity(i));
        }
        savedPizzaEntity.setLocationList(locationEntitiess);

        when(mockPizzaFinderRepository.getOne(any())).thenReturn(null);
        when(mockPizzaFinderRepository.save(any())).thenReturn(savedPizzaEntity);

        Pizza savedPizza = pizzaFinderService.updatePizzaDetails(null , pizza);

        assertNotNull(savedPizza);
        assertEquals(null, savedPizza.getId());
        assertEquals(pizza.getDescription(), savedPizza.getDescription());
        assertEquals(pizza.getName(), savedPizza.getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.getSpiceLevel());
        assertEquals(locationEntitiess, locationEntitiess);
    }

    @Test
    public void testupdatePizzaLocations(){

        List<Location> locations = new ArrayList<Location>();
        Location location = new Location();

        List<LocationEntity> locationsEn = new ArrayList<>();
        LocationEntity locationEn = new LocationEntity();

        List<LocationEntity> locationsEntity = new ArrayList<>();
        LocationEntity locationEntity = new LocationEntity();

        Pizza pizza = new Pizza();

        location.setId("5");
        location.setAdress("Strada Anton Pann 9, Baia Mare, Romania");
        location.setGoogleId("ChIJqb5T8HncN0cRwluXu2qpYzI");
        location.setLatitude("47.65700518066121");
        location.setLongitude("23.592689037322998");
        location.setPlaceName("Clubul Campionilor");

        locations.add(location);

        pizza.setLocations(locations);
        pizza.setId("1");

        PizzaEntity pizzaEntity = new PizzaEntity();

        locationEn.setId("5");
        locationEn.setGoogleId("ChIJqb5T8HncN0cRwluXu2qpYzI");
        locationEn.setAddress("Strada Anton Pann 9, Baia Mare, Romania");
        locationEn.setLatitude("47.65700518066121");
        locationEn.setLongitude("23.592689037322998");
        locationEn.setPlaceName("Clubul Campionilor");

        locationsEn.add(locationEn);

        locationEntity.setGoogleId("CHHsadkahdksbadjsadksa");
        locationEntity.setAddress("Podul Viilor, Baia Mare, Romania");
        locationEntity.setLatitude("47.75700518066121");
        locationEntity.setLongitude("23.892689037322998");
        locationEntity.setPlaceName("Barbarosa");

        locationsEntity.add(locationEn);
        locationsEntity.add(locationEntity);


        PizzaEntity savedPizzaEntity = new PizzaEntity();

        savedPizzaEntity.setLocationList(locationsEn);
        savedPizzaEntity.setId("1");

        pizzaEntity.setLocationList(locationsEntity);

        when(mockPizzaFinderRepository.getOne(any())).thenReturn(pizzaEntity);
        when(mockPizzaFinderRepository.save(any())).thenReturn(savedPizzaEntity);

        Pizza savedPizza = pizzaFinderService.updatePizzaDetails("1", pizza);


        assertNotNull(savedPizza);
        assertEquals("1", savedPizza.getId());
        assertEquals(pizza.getDescription(), savedPizza.getDescription());
        assertEquals(pizza.getName(), savedPizza.getName());
        assertEquals(pizza.getSpiceLevel(), savedPizza.getSpiceLevel());
        assertEquals(pizza.getLocations().get(0).getId(), savedPizza.getLocations().get(0).getId());
    }

    @Test
    public void testRemovePizza(){
        Pizza removePizza = new Pizza();

        removePizza.setId("1");
        removePizza.setName("Test removePizza");
        removePizza.setDescription("Description");
        removePizza.setSpiceLevel(SpiceLevel.CRAZY);

        doNothing().when(mockPizzaFinderRepository).deleteById("1");

        pizzaFinderService.removePizza("1");
    }

    @Test
    public void testGetAllSpiceLevel(){

        List<SpiceLevel> savedSpiceLevel = pizzaFinderService.getAllSpiceLevel();

        assertEquals(savedSpiceLevel.size(), 3);
        assertEquals(savedSpiceLevel.get(0),  SpiceLevel.NONE);
        assertEquals(savedSpiceLevel.get(1),  SpiceLevel.HOT);
        assertEquals(savedSpiceLevel.get(2),  SpiceLevel.CRAZY);

    }
}


