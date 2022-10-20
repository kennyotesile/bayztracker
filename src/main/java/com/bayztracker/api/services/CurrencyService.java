package com.bayztracker.api.services;

import com.bayztracker.api.entities.Currency;

import java.util.List;

public interface CurrencyService {
    Currency query(String symbol);

    List<Currency> query();

    Currency add(Currency currency);

    void deleteBySymbol(String symbol);

    void validateCurrency(Currency currency);

    void validateCurrencySymbol(String symbol);

    void validateCurrencyName(String name);

    void validateCurrencyPrice(Object currencyPrice);
}
