package com.bayztracker.api;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.entities.Role;
import com.bayztracker.api.repositories.RoleRepository;
import com.bayztracker.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
@EnableScheduling
public class ApiApplication{

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@GetMapping()
	ResponseEntity<String> displayMessage() {
		return ResponseEntity.ok().body("API is running");
	}

	@GetMapping("/logout/success")
	ResponseEntity<String> logoutSuccess() {
		return ResponseEntity.ok().body("You have successfully logged out of the application");
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.createRole(new Role(null,"ROLE_USER"));
			userService.createRole(new Role(null,"ROLE_ADMIN"));

			userService.registerNewUser(new AppUser("user", "pass", new ArrayList<>()));
			userService.registerNewUser(new AppUser("admin", "pass", new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");
		};
	}



}
