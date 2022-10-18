package com.bayztracker.api.application;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.services.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleTask {

    @Autowired
    AlertService alertService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(fixedDelay = 30000)
    public void checkAlertsAndNotify() {
        List<Alert> alerts = alertService.findAll();

        for (Alert alert : alerts) {
            // If status is NEW
            if (alert.getStatus().equals(Alert.Status.NEW)) {
                // If targetPrice is reached (that is currentPrice is GREATER THAN OR EQUAL to targetPrice)
                if (alert.getCurrency().getCurrentPrice().compareTo(alert.getTargetPrice()) >= 0) {
                    alert.setStatus(Alert.Status.TRIGGERED);
                    alertService.update(alert);
                }
            }

            if (alert.getStatus().equals(Alert.Status.TRIGGERED)) {
                logger.info("Target price of " + alert.getTargetPrice() + " reached for " + alert.getCurrency().getSymbol());
            }
        }
    }
}
