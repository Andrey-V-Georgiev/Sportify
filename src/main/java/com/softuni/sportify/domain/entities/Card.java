package com.softuni.sportify.domain.entities;

import com.softuni.sportify.constants.CardType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    private CardType cardType;
    private BigDecimal price;
    private LocalDateTime expiryDate;

    public Card() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "expiry_date", nullable = false)
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
