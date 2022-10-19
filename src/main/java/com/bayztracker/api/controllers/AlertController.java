package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Alert;
import com.bayztracker.api.entities.AlertRequestModel;
import com.bayztracker.api.exceptions.BadRequestException;
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
    public ResponseEntity<Alert> editAlert(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> params, @RequestBody(required = false) Map<String, Object> body) throws Exception {
        if (!(params.isEmpty()) && body == null) { // when there is/are request PARAM(S) but no body
            Alert updatedAlert;

            if (params.containsKey("status")) {
                Alert.Status alertStatus = Alert.Status.valueOf(((String) params.get("status")).toUpperCase());
                System.out.println(alertStatus);
                updatedAlert = alertService.update(id, alertStatus);

                return ResponseEntity.status(HttpStatus.OK).body(updatedAlert);
            } else {
                throw new Exception("Unknown request");
            }
        } else if (!(body.isEmpty()) && params.isEmpty()) { // when there is BODY but no parameter(s)
            // Data to update the existing alert entity with (from request parameters)
            String currencySymbol;
            BigDecimal targetPrice;

            if (body.containsKey("currencySymbol")) {
                currencySymbol = (String) body.get("currencySymbol");
                alertService.update(id, currencySymbol);
            }
            if (body.containsKey("targetPrice")) {
                if (body.get("targetPrice") instanceof BigDecimal)
                    targetPrice = (BigDecimal) body.get("targetPrice");
                else if (body.get("targetPrice") instanceof Number)
                    targetPrice = new BigDecimal(body.get("targetPrice").toString());
                else if (body.get("targetPrice") instanceof String)
                    targetPrice = new BigDecimal((String) body.get("targetPrice"));
                else throw new BadRequestException("Target price must be a valid amount in figures");

                alertService.update(id, targetPrice);
            }

//            alertService.update(id, Alert.Status.NEW);
            return ResponseEntity.status(HttpStatus.OK).body(alertService.findById(id));
        } else {
            throw new BadRequestException("Bad request made");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alert> patchAlert(@PathVariable Long id, @RequestParam(required = false) Map<String, Object> params, @RequestBody(required = false) Map<String, Object> body) throws Exception {
        return editAlert(id, params, body);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        alertService.deleteById(id);
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
