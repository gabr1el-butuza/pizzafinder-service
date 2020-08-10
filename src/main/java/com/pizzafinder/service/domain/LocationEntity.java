package com.pizzafinder.service.domain;

import com.pizzafinder.service.dto.Location;
import javax.persistence.*;

@Entity
@Table(name = "location")
public class LocationEntity extends BaseEntity {


    @Column(name = "googleId", unique = true, nullable = false)
    private String googleId;

    @Lob
    private String address;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "phonePlace")
    private String phonePlace;

    @Column(name = "placePhone")
    private String placePhone;

    @Column(name = "placeWebsite")
    private String placeWebsite;

    @Column(name = "placeRating")
    private String placeRating;

    @Column(name = "placeType")
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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
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

    public String getPhonePlace() {
        return phonePlace;
    }

    public void setPhonePlace(String phonePlace) {
        this.phonePlace = phonePlace;
    }

    public static Location toLocation(LocationEntity locationEntity) {
        Location location = new Location();

        location.setId(locationEntity.getId());
        location.setGoogleId(locationEntity.getGoogleId());
        location.setAdress(locationEntity.getAddress());
        location.setLatitude(locationEntity.getLatitude());
        location.setLongitude(locationEntity.getLongitude());
        location.setPlaceName(locationEntity.getPlaceName());
        location.setPlacePhone(locationEntity.getPlacePhone());
        location.setPlaceWebsite(locationEntity.getPlaceWebsite());
        location.setPlaceRating(locationEntity.getPlaceRating());
        location.setPlaceType(locationEntity.getPlaceType());
        location.setPhonePlace(locationEntity.getPhonePlace());

        return location;
    }
}
