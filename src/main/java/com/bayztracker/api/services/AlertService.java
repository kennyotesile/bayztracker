package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.AlertRequestModel;

import java.math.BigDecimal;
import java.util.List;

public interface AlertService {
    Alert create(AlertRequestModel alertRequestModel);

    Alert update(Long id, String currencySymbol);

    Alert update(Long id, BigDecimal targetPrice);

    Alert update(Long id, Alert.Status status);

    Alert update(Alert alert);

    void deleteById(Long id);

    Alert findById(Long id);

    List<Alert> findAll();

    Alert changeAlertStatus(Long id, String newStatus);
}
