package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlertController {

    @Autowired
    AlertService alertService;

    @PostMapping()
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        return ResponseEntity.status(HttpStatus.OK).body(null); // TODO: 10/15/2022
    }

    @PatchMapping()
    public void editAlert() {}

    @PatchMapping()
    public void acknowledgeAlert() {}

    @PatchMapping()
    public void cancelAlert() {}

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {}
}
