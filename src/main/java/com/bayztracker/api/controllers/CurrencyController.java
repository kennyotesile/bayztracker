package com.bayztracker.api.controllers;

import com.bayztracker.api.entities.Currency;
import com.bayztracker.api.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping()
    public void addCurrency(@RequestBody Currency currency) {}

    @GetMapping("/{id}")
    public void queryCurrency(@PathVariable Long id) {}

    @DeleteMapping("/{id}")
    public void removeCurrency(@PathVariable Long id) {}
}
