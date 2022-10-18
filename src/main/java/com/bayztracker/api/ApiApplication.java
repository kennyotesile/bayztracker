package com.bayztracker.api;

import com.bayztracker.api.entities.AppUser;
import com.bayztracker.api.entities.Roles;
import com.bayztracker.api.repositories.RoleRepository;
import com.bayztracker.api.repositories.UserRepository;
import com.bayztracker.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

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
	String displayMessage() {
		return "API is running";
	}


	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.createRole(new Roles(null,"ROLE_USER"));
			userService.createRole(new Roles(null,"ROLE_ADMIN"));

			userService.registerNewUser(new AppUser("otesh@otesh.com","1234",new ArrayList<>()));
			userService.registerNewUser(new AppUser("otesh@test.com","1234",new ArrayList<>()));

			userService.addRoleToUser("otesh@otesh.com", "ROLE_USER");
			userService.addRoleToUser("otesh@test.com", "ROLE_ADMIN");
		};
	}



}
