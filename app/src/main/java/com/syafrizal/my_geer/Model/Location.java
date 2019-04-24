package com.syafrizal.my_geer.Model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    String position;
    String address;
    String name;
    Restaurant restaurant;

    public Location() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Location{" +
                "position='" + position + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
