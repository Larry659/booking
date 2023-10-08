package com.book.shop.service;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AccountService {
    ResponseEntity<?> createAccount(AccountRequest payload);
    ApiResponse<?> updatedAccount(Long id, AccountRequest load);
    ApiResponse<?> listAccounts();
    ApiResponse<?> deleteAccount(Long id);
}
