package com.book.shop.route;



import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.auth.AuthenticationRequest;
import com.book.shop.dto.auth.AuthenticationResponse;
import com.book.shop.dto.auth.RegisterRequest;

import com.book.shop.model.Accounts;
import com.book.shop.password.PasswordRequest;
import com.book.shop.password.PasswordResetTokenService;
import com.book.shop.repo.AccountRepo;
import com.book.shop.service.security.AuthenticationService;
import com.book.shop.serviceImpl.AccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private  final AccountRepo accountRepo;
    private final PasswordResetTokenService passwordResetTokenService;

    @PostMapping("/signup")
    public ApiResponse<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        return authenticationService.login(request);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> signOut(){
       return ResponseEntity.ok(authenticationService.logoutUser());
    }


   

}
