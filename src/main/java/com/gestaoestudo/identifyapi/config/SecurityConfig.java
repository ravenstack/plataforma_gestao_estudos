package com.gestaoestudo.identifyapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita o CSRF para o console do H2 conseguir funcionar
                .csrf(csrf -> csrf.disable())

                // 2. Permite que o H2 apareça dentro de frames
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // 3. Libera os caminhos necessários
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/all", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}