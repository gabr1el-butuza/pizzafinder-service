package com.pizzafinder.service.dto;

import java.util.List;

public class Pizza {

    private String id;

    private String name;

    private String description;

    private SpiceLevel spiceLevel;

    private String picPath;

    private Boolean isVegetarian;

    private Boolean isVegan;

    private Boolean isLactoVegetarian;

    private Boolean isOvoVegetarian;

    private List<Location> locations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getIsVegetarian(){ return isVegetarian; }

    public void setIsVegetarian(Boolean isVegetarian){ this.isVegetarian = isVegetarian; }

    public Boolean getIsLactoVegetarian(){ return isLactoVegetarian; }

    public void setIsLactoVegetarian(Boolean isLactoVegetarian){ this.isLactoVegetarian = isLactoVegetarian; }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public Boolean getIsOvoVegetarian(){ return isOvoVegetarian; }

    public void setIsOvoVegetarian(Boolean isOvoVegetarian) {this.isOvoVegetarian = isOvoVegetarian; }

    public List<Location> getLocations() {return locations; }

    public void setLocations(List<Location> locations) { this.locations = locations; }

}
