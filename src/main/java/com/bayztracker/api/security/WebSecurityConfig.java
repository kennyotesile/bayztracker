package com.bayztracker.api.security;

import com.bayztracker.api.filter.CustomAuthenticationFilter;
import com.bayztracker.api.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        http.csrf().disable().authorizeRequests()
                .antMatchers(GET, "/api").permitAll()
                .antMatchers(GET, "/currencies/**").permitAll()
                .antMatchers(POST, "/login/**").permitAll()

                .antMatchers(GET, "/api/").permitAll()
                .antMatchers(GET, "/api/users/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(POST, "/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/")
                .invalidateHttpSession(true)
                .and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
