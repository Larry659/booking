package com.book.shop.route;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.ApiResponse;
import com.book.shop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController

@RequestMapping(value ="/api/v1/account")

@RequiredArgsConstructor
public class AccoutRoute {
    private final AccountService accountService;

//    @PostMapping("/add")
//    ResponseEntity addAccount(@RequestBody AccountRequest payload) {
//        return accountService.createAccount(payload);
//    }
    @PostMapping("/update")
    ApiResponse<?> updateAccount(@RequestParam Long id, @RequestBody AccountRequest payload) {
        return accountService.updatedAccount(id,payload);
    }
    @GetMapping("/fetch")
    ApiResponse<?> getAccount() {

        return accountService.listAccounts();
    }
    @DeleteMapping("/delete")
    ApiResponse<?> deleteAccount(@RequestParam Long id) {
        return accountService.deleteAccount(id);
    }

    @GetMapping("/count")
    ApiResponse<?> getAccountCount() {
        return accountService.countAccounts();
    }

    @GetMapping("/count_month")
    ApiResponse<?> getAccountCountForMonth() {
        return accountService.countAccountForMonth();
    }
}
