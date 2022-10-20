package com.bayztracker.api.repositories;

import com.bayztracker.api.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
