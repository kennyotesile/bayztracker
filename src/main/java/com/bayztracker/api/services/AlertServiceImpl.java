package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.AlertRequestModel;
import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.repositories.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);

    @Override
    public Alert create(AlertRequestModel alertRequestModel) {
        String currencySymbol = alertRequestModel.getCurrencySymbol();

        currencyService.validateCurrencySymbol(currencySymbol);
        Currency currency = currencyService.query(currencySymbol);

        Alert alert = new Alert();

        alert.setCurrency(currency);

        currencyService.validateCurrencyPrice(alertRequestModel.getTargetPrice());
        alert.setTargetPrice(BigDecimal.valueOf(((Number) alertRequestModel.getTargetPrice()).doubleValue()));

        alert.setStatus(Alert.Status.NEW);

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

        update(id, Alert.Status.NEW);

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, BigDecimal targetPrice) {
        // Alert entity to replace with new data
        Alert alert = findById(id);
        alert.setTargetPrice(targetPrice);

        update(id, Alert.Status.NEW);

        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, Alert.Status status) {
        Alert alert = findById(id);

        // If targetPrice is reached
        if (alert.getCurrency().getCurrentPrice().compareTo(alert.getTargetPrice()) >= 0) {
            // Don't permit if asked to change to NEW; default to TRIGGERED
            if (status.equals(Alert.Status.NEW)) {
                status = Alert.Status.TRIGGERED;
            }
            alert.setStatus(status);
            logger.info("The status for the alert " + alert + " has been updated to " + status);
            return alertRepository.save(alert);
        }

        // If targetPrice not reached
        else {
            // Permit if asked to change to NEW or CANCELLED
            if (status.equals(Alert.Status.NEW) || status.equals(Alert.Status.CANCELLED)) {
                alert.setStatus(status);
                logger.info("The status for the alert " + alert + " has been updated to " + status);

                return alertRepository.save(alert);
            }
            // Don't permit if asked to change to other
            else {
                return alert;
            }
        }
    }

    @Override
    public Alert update(Alert alert) {
        currencyService.validateCurrencyPrice(alert.getTargetPrice());
        currencyService.validateCurrencyName(alert.getCurrency().getName());
        currencyService.validateCurrencySymbol(alert.getCurrency().getSymbol());

        alert.setStatus(Alert.Status.NEW);

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

    @Override
    public Alert changeAlertStatus(Long id, String newStatus) {
        Alert.Status status = Alert.Status.valueOf(newStatus.toUpperCase());
        return update(id, status);
    }
}
