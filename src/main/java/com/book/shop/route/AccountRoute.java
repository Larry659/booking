package com.book.shop.route;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.ApiResponse;
import com.book.shop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value ="/api/v1/accounts")

@RequiredArgsConstructor
public class AccountRoute {
    private final AccountService accountService;

//    @PostMapping("/add")
//    ResponseEntity addAccount(@RequestBody AccountRequest payload) {
//        return accountService.createAccount(payload);
//    }
    @PostMapping("/{id}")
    ApiResponse<?> updateAccount(@PathVariable Long id, @RequestBody AccountRequest payload) {
        return accountService.updatedAccount(id,payload);
    }
    @GetMapping("")
    ApiResponse<?> getAccount() {

        return accountService.listAccounts();
    }
    @DeleteMapping("/{id}")
    ApiResponse<?> deleteAccount(@PathVariable Long id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/count")
    ApiResponse<?> getAccountCount() {
        return accountService.countAccounts();
    }

    @GetMapping("/month")
    ApiResponse<?> getAccountCountForMonth() {
        return accountService.countAccountForMonth();
    }
}
