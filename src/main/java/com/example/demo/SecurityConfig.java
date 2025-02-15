package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                        .requestMatchers("/app/public/**").permitAll()  // Allow everyone to access "/app/public/**"
                        .requestMatchers("/app/admin/**").hasRole("ADMIN") // Only users with "ADMIN" role can access "/app/admin/**"
                        .requestMatchers("/app/user/**").hasAnyRole("USER", "ADMIN") // Users with "USER" or "ADMIN" role can access "/app/user/**"
                )
                // Enabling HTTP Basic authentication
                .httpBasic()
                .and()
                // Customizing logout (optional, but if you need a logout URL)
                .logout(logout -> logout
                        .logoutUrl("/app/logout") // Custom logout URL
                        .logoutSuccessUrl("/app/login?logout") // Redirect to login page after logout
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
