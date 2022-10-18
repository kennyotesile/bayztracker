//package com.bayztracker.api.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/currencies").permitAll()//hasRole("USER")
//
//                .antMatchers("/", "/login", "/logout", "/error").permitAll()
//                .antMatchers("/**").permitAll()
//                .and().formLogin().loginPage("/login")
//                .and().httpBasic(withDefaults());
//
//        return http.build();
//    }
//}
