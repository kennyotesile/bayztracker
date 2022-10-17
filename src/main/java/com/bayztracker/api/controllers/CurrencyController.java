package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping()
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        Currency createdCurrency = currencyService.add(currency);
        return ResponseEntity.ok().body(createdCurrency);
    }

    @GetMapping(value = {"", "/{symbol}"})
    public ResponseEntity<Object> queryCurrency(@PathVariable(required = false) String symbol) {
        if (symbol != null)
            return ResponseEntity.ok().body(currencyService.query(symbol));
        else
            return ResponseEntity.ok().body(currencyService.query());
    }

    @DeleteMapping("/{symbol}")
    public void removeCurrency(@PathVariable String symbol) {}
}
