package com.bayztracker.api.services;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.entities.Roles;
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

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public void registerNewUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void createRole(Roles roles) {
        roleRepository.save(roles);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<AppUser> appUser = this.getUserWithUsername(username);
        Roles roles = roleRepository.findByRole(roleName);

        appUser.ifPresent(user -> user.getRole().add(roles));
    }

    @Override
    public Optional<AppUser> getUserWithUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
