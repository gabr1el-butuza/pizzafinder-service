package com.pizzafinder.service.dto;

import com.pizzafinder.service.domain.LocationEntity;

public class Location {

    private String id;
    private String googleId;
    private String address;
    private String latitude;
    private String longitude;
    private String placeName;
    private String phonePlace;
    private String placePhone;
    private String placeWebsite;
    private String placeRating;
    private String placeType;

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(String placeRating) {
        this.placeRating = placeRating;
    }

    public String getPlaceWebsite() {
        return placeWebsite;
    }

    public void setPlaceWebsite(String placeWebsite) {
        this.placeWebsite = placeWebsite;
    }

    public String getPlacePhone() {
        return placePhone;
    }

    public void setPlacePhone(String placePhone) {
        this.placePhone = placePhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPhonePlace() { return phonePlace; }

    public void setPhonePlace (String phonePlace) { this.phonePlace = phonePlace; }

    public static LocationEntity toLocationEntity(Location location){
        LocationEntity locc = new LocationEntity();

        locc.setId(location.getId());
        locc.setGoogleId(location.getGoogleId());
        locc.setAddress(location.getAddress());
        locc.setLatitude(location.getLatitude());
        locc.setLongitude(location.getLongitude());
        locc.setPlaceName(location.getPlaceName());
        locc.setPhonePlace(location.getPhonePlace());
        locc.setPlacePhone(location.getPlacePhone());
        locc.setPlaceWebsite(location.getPlaceWebsite());
        locc.setPlaceRating(location.getPlaceRating());
        locc.setPlaceType(location.getPlaceType());

        return locc;
    }
}
