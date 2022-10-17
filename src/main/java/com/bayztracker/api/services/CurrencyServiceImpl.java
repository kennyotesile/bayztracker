package com.bayztracker.api.services;

import com.bayztracker.api.constants.Constants;
import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.exceptions.UnsupportedCurrencyCreationException;
import com.bayztracker.api.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency add(Currency currency) {
        for(String el : Constants.unsupportedCurrencySymbols) {
            if (currency.getSymbol().equalsIgnoreCase(el)) {
                throw new UnsupportedCurrencyCreationException("Currency symbol not supported.");
            }
        }
        return currencyRepository.save(currency);
    }

    @Override
    public Currency query(String symbol) {
        Optional<Currency> currency = currencyRepository.findBySymbol(symbol);
        if (currency.isPresent())
            return currency.get();
        else throw new NotFoundException("Currency does not exist.");
    }

    @Override
    public List<Currency> query() {
        return currencyRepository.findAll();
    }
}
