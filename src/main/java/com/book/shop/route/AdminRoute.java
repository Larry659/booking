package com.book.shop.route;

import com.book.shop.dto.AccountRequest;
import com.book.shop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/admin/accounts")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminRoute {
    private final AccountService accountService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("Welcome to the ADMIN Booking Home Page");
    }

    @PostMapping
    ResponseEntity<?> addAccount(@RequestBody AccountRequest payload) {
        return accountService.createAccount(payload);
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody AccountRequest payload) {
        return accountService.updatedAccount(id,payload);
    }
    @GetMapping
    ResponseEntity<?> getAccount() {
        return accountService.listAccounts();
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable String id) {
        return accountService.deleteAccount(id);
    }
}
