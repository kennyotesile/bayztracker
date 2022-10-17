package com.bayztracker.api.repositories;

import com.bayztracker.api.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findBySymbol(String symbol);
}
