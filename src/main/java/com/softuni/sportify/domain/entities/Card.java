package com.softuni.sportify.domain.entities;

import com.softuni.sportify.constants.CardType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Card extends BaseEntity {

    private CardType type;
    private BigDecimal price;
    private LocalDateTime expiryDate;

    public Card() {
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
