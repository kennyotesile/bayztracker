package com.bayztracker.api.entities;

import java.math.BigDecimal;

public class AlertRequestModel {

    private String currencySymbol;
    private BigDecimal targetPrice;

    public AlertRequestModel() {}

    public AlertRequestModel(String currencySymbol, BigDecimal targetPrice) {
        this.currencySymbol = currencySymbol;
        this.targetPrice = targetPrice;
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
}
