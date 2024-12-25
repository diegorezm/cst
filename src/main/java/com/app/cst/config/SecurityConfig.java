package com.app.cst.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          return http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/about").permitAll()
                    .requestMatchers("/services").permitAll()
                    .requestMatchers("/styles/**").permitAll()
                    .requestMatchers("/icons/**").permitAll()
                    .requestMatchers("/images/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated())
                    .formLogin(form -> form
                              .loginPage("/auth/login")
                              .failureUrl("/auth/login?error=true")
                              .defaultSuccessUrl("/dashboard", true))
                    .logout(config -> config
                              .logoutUrl("/auth/logout")
                              .logoutSuccessUrl("/"))
                    .build();
     }

     @Bean
     public AuthenticationFailureHandler authenticationFailureHandler() {
          return new AuthFailureHandler();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }
}
