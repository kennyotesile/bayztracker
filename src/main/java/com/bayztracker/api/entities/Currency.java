package com.bayztracker.api.entities;

import java.math.BigDecimal;
import java.util.Date;

// TODO: 13/10/2022 Don't support [ETH, LTC, ZKN, MRD, LPR, GBZ]
public class Currency {
    String name;
    String symbol;
    BigDecimal currentPrice;
    Date createdTime;
    boolean enabled;

    public Currency(String name, String symbol, BigDecimal currentPrice, Date createdTime, boolean enabled) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.createdTime = createdTime;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
