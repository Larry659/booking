package com.book.shop.service.security;


import com.book.shop.config.JwtService;
import com.book.shop.dto.auth.AuthenticationRequest;
import com.book.shop.dto.auth.AuthenticationResponse;
import com.book.shop.dto.auth.RegisterRequest;
import com.book.shop.enums.AccountType;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepo accountRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = accountRepo.findAccountsByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("The User with this email not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Accounts
                .builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .dateCreated(LocalDateTime.now())
                .userName(request.getUserName())
                .accountType(AccountType.USER)
                .build();
        accountRepo.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
