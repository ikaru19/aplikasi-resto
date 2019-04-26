package com.syafrizal.my_geer.Model;

import java.util.Date;

public class BookingDishes {
    private Integer id;
    private Integer booking_id;
    private Integer dish_id;
    private Integer quantity;

    private Date created_at;
    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Integer getDish_id() {
        return dish_id;
    }

    public void setDish_id(Integer dish_id) {
        this.dish_id = dish_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "BookingDishes{" +
                "id=" + id +
                ", booking_id=" + booking_id +
                ", dish_id=" + dish_id +
                ", quantity=" + quantity +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}



