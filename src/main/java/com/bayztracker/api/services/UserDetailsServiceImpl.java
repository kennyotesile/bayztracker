package com.bayztracker.api.services;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<AppUser> userData = userRepository.findByUsername(username.toLowerCase());
        if (!(userData.isPresent())) {
            throw new UsernameNotFoundException("User with the username, " + username + " was not found.");
        }

        AppUser appUser = userData.get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
