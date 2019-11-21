package com.softuni.sportify.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Event event;
    private Account account;
    private LocalDateTime orderDate;
    private boolean paymentConfirmed;
    private boolean coveredByCardType;
    private BigDecimal extraCharge;

    public Order() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="account_id", nullable=false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "order_date", nullable = false)
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "payment_confirmed", nullable = false)
    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public void setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
    }

    @Column(name = "covered_by_card_type", nullable = false)
    public boolean isCoveredByCardType() {
        return coveredByCardType;
    }

    public void setCoveredByCardType(boolean coveredByCardType) {
        this.coveredByCardType = coveredByCardType;
    }

    @Column(name = "extra_charge", nullable = false)
    public BigDecimal getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(BigDecimal extraCharge) {
        this.extraCharge = extraCharge;
    }
}
