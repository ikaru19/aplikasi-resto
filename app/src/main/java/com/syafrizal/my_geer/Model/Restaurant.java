package com.syafrizal.my_geer.Model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    String name;
    String address;
    String picture;
    String description;
    String phone;
    List<Menu> menus = null;
    List<Dish> dishes = new ArrayList<>();

    public Restaurant(String name, String address, String picture) {
        this.name = name;
        this.address = address;
        this.picture = picture;
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
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
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", menus=" + menus +
                ", dishes=" + dishes +
                '}';
    }
}
