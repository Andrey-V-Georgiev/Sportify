package com.softuni.sportify.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

public class ShoppingCart extends BaseEntity {

    private User user;
    private LocalDateTime expiryDate;
    private Set<Order> orders;

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
