package com.pizzafinder.service.domain;

import com.pizzafinder.service.dto.Location;
import com.pizzafinder.service.dto.Pizza;
import com.pizzafinder.service.dto.SpiceLevel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.pizzafinder.service.domain.LocationEntity.toLocation;

@Entity
@Table(name = "pizza")
public class PizzaEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private SpiceLevel spiceLevel;

    @Column(name="pic_path")
    private String picPath;

    @Column(name="isVegetarian")
    private Boolean isVegetarian;

    @Column(name = "isVegan")
    private Boolean isVegan;

    @Column(name = "isLactoVegetarian")
    private Boolean isLactoVegetarian;

    @Column(name = "isOvoVegetarian")
    private Boolean isOvoVegetarian;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="pizzalocation",
            joinColumns={@JoinColumn(name="pizzaId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="locationId", referencedColumnName="id")},
            uniqueConstraints = { @UniqueConstraint(columnNames = {
                    "pizzaId", "locationId" }) })
    private List<LocationEntity> locationList = new ArrayList<>();

    public List<LocationEntity> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationEntity> locationList) {
        this.locationList = locationList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpiceLevel getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(SpiceLevel spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public String getPicPath(){ return picPath; }

    public void setPicPath(String picPath){ this.picPath = picPath; }

    public Boolean getIsVegetarian() {return isVegetarian;}

    public void setIsVegetarian(Boolean isVegetarian){ this.isVegetarian = isVegetarian; }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public Boolean getIsLactoVegetarian() {return isLactoVegetarian;}

    public void setIsLactoVegetarian(Boolean isLactoVegetarian){ this.isLactoVegetarian = isLactoVegetarian; }

    public Boolean getIsOvoVegetarian() {return isOvoVegetarian;}

    public void setIsOvoVegetarian(Boolean isOvoVegetarian){ this.isOvoVegetarian = isOvoVegetarian; }

    public static Pizza toPizza(PizzaEntity pizzaEntity) {
        Pizza pizza = new Pizza();

        pizza.setId(pizzaEntity.getId());
        pizza.setName(pizzaEntity.getName());
        pizza.setDescription(pizzaEntity.getDescription());
        pizza.setSpiceLevel(pizzaEntity.getSpiceLevel());
        pizza.setPicPath(pizzaEntity.getPicPath());
        pizza.setIsVegetarian(pizzaEntity.getIsVegetarian());
        pizza.setIsVegan(pizzaEntity.getIsVegan());
        pizza.setIsLactoVegetarian(pizzaEntity.getIsLactoVegetarian());
        pizza.setIsOvoVegetarian(pizzaEntity.getIsOvoVegetarian());


        List<Location> locations = new ArrayList<>();
        for (LocationEntity locationEntity: pizzaEntity.getLocationList()){
            locations.add(toLocation(locationEntity));
        }
        pizza.setLocations(locations);
        return pizza;
    }
}
