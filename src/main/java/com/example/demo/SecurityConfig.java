package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Authorizing HTTP requests with roles
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Only users with "ADMIN" role can access "/admin/**"
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Users with "USER" or "ADMIN" role can access "/user/**"
                        .requestMatchers("/public/**").permitAll() // No authentication required for "/public/**"
                )
                // Customizing form login
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .permitAll() // Allows everyone to access the login page
                )
                // Customizing logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // Custom logout URL (default is /logout)
                        .logoutSuccessUrl("/login?logout") // After logout, redirect to the login page with "logout" message
                        .permitAll() // Allows everyone to access logout functionality
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.withUsername("user")
                .password(passwordEncoder().encode("password")) // Encodes password with BCrypt
                .roles("USER") // Assign "USER" role
                .build();

        var admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminpassword")) // Encodes password with BCrypt
                .roles("ADMIN") // Assign "ADMIN" role
                .build();

        return new InMemoryUserDetailsManager(user1, admin); // Creates in-memory user details manager with the two users
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt is a secure and widely-used password encoder
    }
}