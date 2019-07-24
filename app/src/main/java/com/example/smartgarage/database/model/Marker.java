package com.example.smartgarage.database.model;

public class Marker {

    private int id;
    private String name;
    private String address;
    private String lng;
    private String lat;

    public Marker(String name, String address, String lng, String lat) {
        this.name = name;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
