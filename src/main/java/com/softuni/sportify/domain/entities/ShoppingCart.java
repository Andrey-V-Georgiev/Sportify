package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart extends BaseEntity {

    private Account account;
    private LocalDateTime expiryDate;
    private Set<Order> orders;

    public ShoppingCart() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "expiry_date", nullable = false)
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shopping_cart_id")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
