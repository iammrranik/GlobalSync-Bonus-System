package com.globalsync.bonus.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF since this is a stateless REST API tested via Postman
                .csrf(csrf -> csrf.disable())

                // Define explicit Role-Based Access Control rules
                .authorizeHttpRequests(auth -> auth
                        // 1. Only MANAGERS can submit reviews and trigger calculations
                        .requestMatchers(HttpMethod.POST, "/api/bonus-records/calculate").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/performance-reviews/**").hasRole("MANAGER")

                        // 2. Only ADMINS can manage raw employee profiles or drop records
                        .requestMatchers("/api/employees/**").hasRole("ADMIN")

                        // 3. Both ADMIN and EMPLOYEE roles can read bonus record histories
                        .requestMatchers(HttpMethod.GET, "/api/bonus-records/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/performance-reviews/**").hasAnyRole("ADMIN", "MANAGER")

                        // Any other endpoint requests require a basic authenticated user session
                        .anyRequest().authenticated()
                )
                // Use standard HTTP Basic Authentication for testing parameters easily
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("manager123")
                .roles("MANAGER")
                .build();

        UserDetails employee = User.withDefaultPasswordEncoder()
                .username("employee")
                .password("emp123")
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin, manager, employee);
    }
}