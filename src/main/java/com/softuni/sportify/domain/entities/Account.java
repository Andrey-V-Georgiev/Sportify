package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    private User user;
    private Card card;
    private BigDecimal moneyBalance;
    private LocalDateTime creationDate;
    private ShoppingCart shoppingCart;
    private Set<Order> ordersHistory;

    public Account() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name="card_id")
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Column(name = "money_balance")
    public BigDecimal getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(BigDecimal moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    @Column(name = "creation_date", nullable = false)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }


    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @OneToMany(mappedBy = "account")
    public Set<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public void setOrdersHistory(Set<Order> ordersHistory) {
        this.ordersHistory = ordersHistory;
    }
}
