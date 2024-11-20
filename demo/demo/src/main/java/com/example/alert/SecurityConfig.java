package com.example.alert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (테스트 용도, 필요에 따라 활성화 가능)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/alert", "/api/alerts").permitAll()  // /api/alert 및 /api/alerts 경로 허용
                        .anyRequest().authenticated()  // 그 외 경로는 인증 필요
                );

        return http.build();
    }
}
