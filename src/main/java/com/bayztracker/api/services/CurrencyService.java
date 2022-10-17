package com.bayztracker.api.services;

import com.bayztracker.api.entities.Currency;

import java.util.List;

public interface CurrencyService {
    Currency query(String symbol);

    List<Currency> query();

    Currency add(Currency currency);
}
