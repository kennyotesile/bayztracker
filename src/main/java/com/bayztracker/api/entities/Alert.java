package com.bayztracker.api.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Alert {
    Currency currency;
    BigDecimal targetPrice;
    Date createdAt;
    Status status;

    public Alert(Currency currency, BigDecimal targetPrice, Date createdAt, Status status) {
        this.currency = currency;
        this.targetPrice = targetPrice;
        this.createdAt = createdAt;
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

enum Status {
    NEW,
    TRIGGERED,
    ACKED,
    CANCELLED
}