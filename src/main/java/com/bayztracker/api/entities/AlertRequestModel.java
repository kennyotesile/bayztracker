package com.bayztracker.api.entities;

import java.math.BigDecimal;

public class AlertRequestModel {

    private String currencySymbol;
    private BigDecimal targetPrice;
    private Alert.Status status = Alert.Status.NEW;

    public AlertRequestModel() {}

    public AlertRequestModel(String currencySymbol, BigDecimal targetPrice, Alert.Status status) {
        this.currencySymbol = currencySymbol;
        this.targetPrice = targetPrice;
        this.status = status;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public Alert.Status getStatus() {
        return status;
    }

    public void setStatus(Alert.Status status) {
        this.status = status;
    }
}
