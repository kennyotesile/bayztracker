package com.bayztracker.api.repositories;

import com.bayztracker.api.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findByRole(String role);
}
