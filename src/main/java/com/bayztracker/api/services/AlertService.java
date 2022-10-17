package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AlertService {
    Alert create(Map<String, Object> body);

    Alert update(Long id, String currencySymbol, BigDecimal targetPrice, Alert.Status status);

    void deleteById(Long id);

    Alert findById(Long id);

    List<Alert> findAll();
}
