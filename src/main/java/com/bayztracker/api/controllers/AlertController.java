package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.services.AlertService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping()
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert createdAlert = alertService.create(alert);
        return ResponseEntity.status(HttpStatus.OK).body(createdAlert); // TODO: 10/15/2022
    }

    @PutMapping("/{id}")
    public void editAlert(@RequestParam(required = false) String currencySymbol) {}

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {}

    @PatchMapping("/{id}/status")
    public void editStatus() {}
}
