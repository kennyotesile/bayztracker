package com.bayztracker.api.security;

import com.bayztracker.api.filters.CustomAuthenticationFilter;
import com.bayztracker.api.filters.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        http.csrf().disable().authorizeRequests()
                .antMatchers(POST, "/login/**").permitAll()
                .antMatchers(POST, "/logout").permitAll()

                .antMatchers(GET, "/").permitAll()

                .antMatchers(POST, "/currencies").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(GET, "/currencies/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(GET, "/currencies").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(DELETE, "/currencies/**").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers(POST, "/alerts").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(PUT, "/alerts/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(PATCH, "/alerts/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(POST, "/alerts/:alert?status=*").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(DELETE, "/alerts/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(GET, "/alerts/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(GET, "/alerts").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                .antMatchers(GET, "/logout/success").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilter(authenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success")
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
                .passwordEncoder(passwordEncoder());
    }
}
