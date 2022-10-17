package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.services.AlertService;
import com.bayztracker.api.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping()
    public ResponseEntity<Alert> createAlert(@RequestBody Map<String, Object> body) {
        if (body.containsKey("currencySymbol") && body.containsKey("targetPrice") && body.containsKey("status")) {
            Alert createdAlert = alertService.create(body);
            return ResponseEntity.status(HttpStatus.OK).body(createdAlert);
        } else {
            throw new IllegalArgumentException("Invalid request body");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alert> editAlert(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> params) {
        // Data to update the existing alert entity with (from request parameters)
        String currencySymbol = null;
        BigDecimal targetPrice = null;
        Alert.Status status = null;
        if (params.containsKey("currencySymbol")) {
            currencySymbol = (String) params.get("currencySymbol");
        }
        if (params.containsKey("targetPrice")) {
            targetPrice = (BigDecimal) params.get("targetPrice");
        }
        if (params.containsKey("status")) {
            status = (Alert.Status) params.get("status");
        }

        Alert updatedAlert = alertService.update(id, currencySymbol, targetPrice, status);
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
}
