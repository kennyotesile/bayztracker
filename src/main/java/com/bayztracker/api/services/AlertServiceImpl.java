package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.exceptions.NotFoundException;
import com.bayztracker.api.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    CurrencyService currencyService;

    @Override
    public Alert create(Map<String, Object> body) {
        String currencySymbol = (String) body.get("currencySymbol");
        Currency currency = currencyService.query(currencySymbol);

        Alert alert = new Alert();
        alert.setCurrency(currency);
        alert.setTargetPrice(new BigDecimal(((Number) body.get("targetPrice")).doubleValue()));
        alert.setStatus(Alert.Status.valueOf((String) body.get("status")));
        return alertRepository.save(alert);
    }

    @Override
    public Alert update(Long id, String currencySymbol, BigDecimal targetPrice, Alert.Status status) {
        // Currency entity (from currencySymbol)
        Currency currency = currencyService.query(currencySymbol);

        // Alert entity to replace with new data
        Alert alert = findById(id);

        // Implementation of updates
        if (currencySymbol != null)
            alert.setCurrency(currency);
        if (targetPrice != null)
            alert.setTargetPrice(targetPrice);
        if (status != null)
            alert.setStatus(status);

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
