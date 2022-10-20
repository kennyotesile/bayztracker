package com.bayztracker.api.services;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.entities.Role;
import com.bayztracker.api.repositories.RoleRepository;
import com.bayztracker.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void registerNewUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<AppUser> appUser = this.getUserWithUsername(username);
        Role role = roleRepository.findByRole(roleName);

        appUser.ifPresent(user -> user.getRole().add(role));
    }

    @Override
    public Optional<AppUser> getUserWithUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
