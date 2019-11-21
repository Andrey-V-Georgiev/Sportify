package com.softuni.sportify.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends BaseEntity {

    private Event event;
    private LocalDateTime orderDate;
    private boolean paymentConfirmed;
    private boolean coveredByCardType;
    private BigDecimal extraCharge;

    public Order() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public void setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
    }

    public boolean isCoveredByCardType() {
        return coveredByCardType;
    }

    public void setCoveredByCardType(boolean coveredByCardType) {
        this.coveredByCardType = coveredByCardType;
    }

    public BigDecimal getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(BigDecimal extraCharge) {
        this.extraCharge = extraCharge;
    }
}
