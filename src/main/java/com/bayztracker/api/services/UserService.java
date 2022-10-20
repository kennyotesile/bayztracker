package com.bayztracker.api.services;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.entities.Role;

import java.util.Optional;

public interface UserService {

   void registerNewUser(AppUser user);

   void createRole(Role role);

   void addRoleToUser(String username, String role);

   Optional<AppUser> getUserWithUsername(String username);
}
