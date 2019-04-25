package com.syafrizal.my_geer.Model;

public class OrderMenu {
    private int total;
    private String name;
    private int price;

    public OrderMenu(String name, int total,int price) {
        this.total = total;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
