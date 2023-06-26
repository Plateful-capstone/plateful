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

                .requestMatchers(
                        "/recipes/create",
                        "/recipes/comment/",
                        "/recipes/*/edit",
                        "/recipes/*/delete",
                        "/profile/update",
                        "/profile",
                        "/api/recipes/search",
                        "/recipes/search/create",
                        "/profile/changeImage",
                        "/recipes/*/comments/create"
                ).authenticated()
                .requestMatchers(
                        "/recipes/*/comments/*/edit",
                        "/recipes/*/comments/*/delete",
                        "/recipes/*/view",
                        "/recipes",
                        "/recipes/{id}",
                        "/register",
                        "/login",
                        "/about",
                        "/static",
                        "/recipes/search",
                        "/logout",
                        "/loading",
                        "/"
                ).permitAll()
                .requestMatchers("/css/**", "/js/**", "/keys.js").permitAll()
        );
        http.formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/profile")
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