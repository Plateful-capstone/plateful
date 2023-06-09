package com.team5.plateful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class PlatefulSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/recipes/create", "/recipes/*/view", "/recipes/comment/", "/recipes/*/edit", "/profile").authenticated()
                .requestMatchers("/recipes", "/recipes/{id}", "/register", "/login", "/logout","/about", "/static", "/recipes/search").permitAll()
                .requestMatchers("/css/**", "/js/**").permitAll()
        );
        http.formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/recipes")
                .failureUrl("/login?error"));
        //logout redirect to /login
        http.logout((logout) -> logout
                .logoutSuccessUrl("/login"));
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
