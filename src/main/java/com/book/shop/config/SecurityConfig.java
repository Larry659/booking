package com.book.shop.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;


    @Bean
    public UserDetailsService userDetailsService () {
        return new AccountUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("accounts/**", "bookings/**", "mails/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("accounts/**", "bookings/**", "mails/**").authenticated()
                .and().formLogin()
                .and().build();
    }


}
