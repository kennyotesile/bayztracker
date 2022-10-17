package com.bayztracker.api.services;

import com.bayztracker.api.entities.Currency;

public interface CurrencyService {
    Object query(String symbol);

    Currency add(Currency currency);
}
