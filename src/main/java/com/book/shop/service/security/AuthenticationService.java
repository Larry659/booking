package com.book.shop.service.security;


import com.book.shop.config.JwtService;
import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.auth.AuthenticationRequest;
import com.book.shop.dto.auth.AuthenticationResponse;
import com.book.shop.dto.auth.RegisterRequest;
import com.book.shop.enums.AccountType;
import com.book.shop.mapper.Mapper;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import com.book.shop.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.book.shop.dto.AppCode.DUPLICATE;
import static com.book.shop.dto.AppCode.OKAY;
import static com.book.shop.dto.MessageUtil.FAILED;
import static com.book.shop.dto.MessageUtil.SUCCESS;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AccountRepo accountRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public ApiResponse<AuthenticationResponse> login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = accountRepo.findAccountsByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("The User with this email not found"));
        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse auth = AuthenticationResponse.builder()
                .token(jwtToken)
                .accountType(user.getAccountType().toString())
                .build();
        return new ApiResponse<>(SUCCESS,OKAY,auth);
    }

    public ApiResponse<AuthenticationResponse> register(RegisterRequest request) {
     Optional<Accounts> acct =  accountRepo.findAccountsByEmail(request.getEmail());
        if(acct.isEmpty()){
            var user = Accounts
                    .builder()
                    .email(request.getEmail())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phone(request.getPhone())
                    .dateCreated(LocalDateTime.now())
                    .appUserName(request.getAppUserName())
                    .accountType(AccountType.USER)
                    .build();
            accountRepo.save(user);

            var jwtToken = jwtService.generateToken(user);

            AuthenticationResponse auth = AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
            return new ApiResponse<>(SUCCESS,OKAY,auth);
        }
        else {
            AuthenticationResponse auth = AuthenticationResponse.builder()
                    .token("Already created")
                    .build();
            return new ApiResponse<>(FAILED,DUPLICATE,auth);
        }

    }


    public ResponseEntity<?> logoutUser() {

        // Invalidate the current user's JWT token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            // You may want to add additional logic here to handle token invalidation
//            // For example, you could add the token to a blacklist.
//            SecurityContextHolder.clearContext();
//        }


        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        //            // You may want to add additional logic here to handle token invalidation
//            // For example, you could add the token to a blacklist.
        SecurityContextHolder.clearContext();
        log.info("login account is: {}", username);
        //refreshTokenService.deleteByAccountId(username);
        return ResponseEntity.ok("User successfully logout");

    }


}
