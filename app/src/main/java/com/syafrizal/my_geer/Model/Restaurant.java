package com.syafrizal.my_geer.Model;

import java.util.List;

public class Restaurant {
    String name;
    String Address;
    String picture;
    List<Menu> menus = null;

    public Restaurant(String name, String address, String picture) {
        this.name = name;
        Address = address;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
}
