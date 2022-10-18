package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.AlertRequestModel;
import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    CurrencyService currencyService;

    @Override
    public Alert create(AlertRequestModel alertRequestModel) {
        String currencySymbol = alertRequestModel.getCurrencySymbol();

        currencyService.validateCurrencySymbol(currencySymbol);
        Currency currency = currencyService.query(currencySymbol);

        Alert alert = new Alert();

        alert.setCurrency(currency);

        currencyService.validateCurrencyPrice(alertRequestModel.getTargetPrice());
        alert.setTargetPrice(new BigDecimal(((Number) alertRequestModel.getTargetPrice()).doubleValue()));

        alert.setStatus(alertRequestModel.getStatus());

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, String currencySymbol) {
        currencyService.validateCurrencySymbol(currencySymbol);

        // Currency entity (from currencySymbol)
        Currency currency = currencyService.query(currencySymbol);

        // Alert entity to replace with new data
        Alert alert = findById(id);
        alert.setCurrency(currency);

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, BigDecimal targetPrice) {
        // Alert entity to replace with new data
        Alert alert = findById(id);
        alert.setTargetPrice(targetPrice);

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, Alert.Status status) {
        // Alert entity to replace with new data
        Alert alert = findById(id);
        alert.setStatus(status);

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Alert alert) {
        currencyService.validateCurrencyPrice(alert.getTargetPrice());
        currencyService.validateCurrencyName(alert.getCurrency().getName());
        currencyService.validateCurrencySymbol(alert.getCurrency().getSymbol());

        return alertRepository.save(alert);
    }

    @Override
    public void deleteById(Long id) {
        alertRepository.deleteById(id);
    }

    @Override
    public Alert findById(Long id) {
        Optional<Alert> alert = alertRepository.findById(id);
        if (alert.isPresent())
            return alert.get();
        else
            throw new NotFoundException("Alert record with specified id not found.");
    }

    @Override
    public List<Alert> findAll() {
        return alertRepository.findAll();
    }
}
