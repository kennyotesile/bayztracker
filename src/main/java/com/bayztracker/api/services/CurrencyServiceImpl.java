package com.bayztracker.api.services;

import com.bayztracker.api.constants.Constants;
import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.exceptions.BadRequestException;
import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.exceptions.RecordAlreadyExistsException;
import com.bayztracker.api.exceptions.UnsupportedCurrencyCreationException;
import com.bayztracker.api.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency query(String symbol) {
        validateCurrencySymbol(symbol);

        Optional<Currency> currency = currencyRepository.findBySymbol(symbol);
        if (currency.isPresent())
            return currency.get();
        else throw new NotFoundException("Currency does not exist.");
    }

    @Override
    public Currency add(Currency currency) {
        validateCurrency(currency);

        try {
            Currency existingCurrency = query(currency.getSymbol());
            throw new RecordAlreadyExistsException("Currency with specified symbol already exists.");
        } catch (NotFoundException ex) {
            try {
                Optional<Currency> existingCurrency = currencyRepository.findByName(currency.getName());
                if (existingCurrency.isPresent())
                    throw new RecordAlreadyExistsException("Currency with specified name already exists.");
            } catch (NotFoundException ex2) {
                currency.setSymbol(currency.getSymbol().toUpperCase());
            }
            return currencyRepository.save(currency);
        }
    }

    @Override
    public void deleteBySymbol(String symbol) {
        currencyRepository.deleteBySymbol(symbol);
    }

    @Override
    public List<Currency> query() {
        return currencyRepository.findAll();
    }

    @Override
    public void validateCurrency(Currency currency) {
        validateCurrencySymbol(currency.getSymbol());
        validateCurrencyName(currency.getName());
        validateCurrencyPrice(currency);
    }

    @Override
    public void validateCurrencySymbol(String symbol) {
        for(String el : Constants.unsupportedCurrencySymbols) {
            if (symbol.equalsIgnoreCase(el)) {
                throw new UnsupportedCurrencyCreationException("Currency symbol not supported.");
            }
        }

        if (symbol == null || symbol.replace("\\s+", "").equals("") || symbol.replace("\\s+", "").equals("null")) {
            throw new BadRequestException("Currency symbol must be valid");
        }
    }

    @Override
    public void validateCurrencyName(String name) {
        if (name == null) {
            throw new BadRequestException("Currency name must be valid");
        }
    }

    @Override
    public void validateCurrencyPrice(Object currencyPrice) {
        if (!(currencyPrice instanceof BigDecimal)) {
            throw new BadRequestException("Current price must be valid");
        }
    }
}
