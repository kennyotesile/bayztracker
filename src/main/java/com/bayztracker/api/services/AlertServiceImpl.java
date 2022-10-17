package com.bayztracker.api.services;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    @Override
    public Alert create(Alert alert) {
        return alertRepository.save(alert);
    }
}
