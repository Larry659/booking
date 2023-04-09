package com.book.shop.service;

import com.book.shop.dto.AccountRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AccountService {
    ResponseEntity createAccount(AccountRequest payload);
    ResponseEntity updatedAccount(String uuid,AccountRequest load);
    ResponseEntity listAccounts();
    ResponseEntity deleteAccount(String uuid);
}
