package com.softuni.sportify.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Account extends BaseEntity {

    private User user;
    private Card card;
    private BigDecimal moneyBalance;
    private LocalDateTime creationDate;
    private Set<Order> orderHistory;

    public Account() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BigDecimal getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(BigDecimal moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Set<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
