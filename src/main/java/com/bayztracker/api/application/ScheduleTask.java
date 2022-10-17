package com.bayztracker.api.application;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.logging.Logger;

@EnableScheduling
public class ScheduleTask {

    @Autowired
    AlertService alertService;

    Logger logger = Logger.getLogger(ScheduleTask.class.getName());

    @Scheduled(fixedDelay = 30000)
    public void checkAlertsAndNotify() {
        List<Alert> alerts = alertService.findAll();

        for (Alert alert : alerts) {

            if (alert.getTargetPrice().compareTo(alert.getCurrency().getCurrentPrice()) <= 0) {
                if (!(alert.getStatus().equals(Alert.Status.TRIGGERED))) {
                    alert.setStatus(Alert.Status.TRIGGERED);
                }
            }

            if (alert.getStatus().equals(Alert.Status.TRIGGERED)) {
                System.out.println("Target price reached");
                logger.info("Target price reached");
            }
        }
    }
}
