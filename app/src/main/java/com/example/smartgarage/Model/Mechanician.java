package com.example.smartgarage.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mechanician {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("names")
    @Expose
    private String names;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("garage_id")
    @Expose
    private Integer garage_id;

    public Mechanician() {
    }

    public Mechanician(String names, String email, String phone, String address, String password) {
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.location = "rwanda";
        this.garage_id = 1;
    }

    public Mechanician(String names, String email, String phone, String address) {
        this.names = names;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGarage_id() {
        return garage_id;
    }

    public void setGarage_id(Integer garage_id) {
        this.garage_id = garage_id;
    }
}