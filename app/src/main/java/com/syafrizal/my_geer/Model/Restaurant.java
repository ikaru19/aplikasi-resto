package com.syafrizal.my_geer.Model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    String id;
    String name;
    String address;
    String picture;
    String image;
    String description;
    String phone;

    List<Dish> dish = new ArrayList<>();

    public Restaurant(String name, String address, String picture) {
        this.name = name;
        this.address = address;
        this.picture = picture;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Dish> getDishes() {
        return dish;
    }

    public void setDishes(List<Dish> dishes) {
        this.dish = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", dishes=" + dish +
                '}';
    }
}
