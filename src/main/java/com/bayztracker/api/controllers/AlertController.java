package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.AlertRequestModel;
import com.bayztracker.api.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping()
    public ResponseEntity<Alert> createAlert(@RequestBody AlertRequestModel alertRequestModel) {
        return ResponseEntity.status(HttpStatus.OK).body(alertService.create(alertRequestModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alert> editAlert(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> params) {
        // Data to update the existing alert entity with (from request parameters)
        String currencySymbol;
        BigDecimal targetPrice;
        Alert.Status status;

        Alert updatedAlert = new Alert();

        if (params.containsKey("currencySymbol")) {
            currencySymbol = (String) params.get("currencySymbol");
            updatedAlert = alertService.update(id, currencySymbol);
        }
        if (params.containsKey("targetPrice")) {
            targetPrice = (BigDecimal) params.get("targetPrice");
            updatedAlert = alertService.update(id, targetPrice);
        }
        if (params.containsKey("status")) {
            status = (Alert.Status) params.get("status");
            updatedAlert = alertService.update(id, status);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedAlert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alert> patchAlert(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> params) {
        return editAlert(id, params);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alertService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.status(HttpStatus.OK).body(alertService.findAll());
    }
}
