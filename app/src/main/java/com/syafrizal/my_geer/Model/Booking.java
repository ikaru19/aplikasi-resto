package com.syafrizal.my_geer.Model;

import java.util.Date;
import java.util.List;

public class Booking {
    private Integer id;
    private Integer users_id;
    private Integer locations_id;
    private Integer total_price;
    private String status;
    private String payment_type;
    private String serving_type;
    private String notes;
    private Date created_at;
    private Date updated_at;
    private List<BookingDishes> booking_dish;
    private List<Dish> dish;
    private Location location;
    private Restaurant restaurant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Integer getLocations_id() {
        return locations_id;
    }

    public void setLocations_id(Integer locations_id) {
        this.locations_id = locations_id;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getServing_type() {
        return serving_type;
    }

    public void setServing_type(String serving_type) {
        this.serving_type = serving_type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<BookingDishes> getBooking_dish() {
        return booking_dish;
    }

    public void setBooking_dish(List<BookingDishes> booking_dish) {
        this.booking_dish = booking_dish;
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", users_id=" + users_id +
                ", locations_id=" + locations_id +
                ", total_price=" + total_price +
                ", status='" + status + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", serving_type='" + serving_type + '\'' +
                ", notes='" + notes + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}



