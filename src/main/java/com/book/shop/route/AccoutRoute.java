package com.book.shop.route;

import com.book.shop.dto.AccountRequest;
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
    ResponseEntity updateAccount(@RequestParam String id, @RequestBody AccountRequest payload) {
        return accountService.updatedAccount(id,payload);
    }
    @GetMapping("/fetch")
    ResponseEntity getAccount() {
        return accountService.listAccounts();
    }
    @DeleteMapping("/delete")
    ResponseEntity deleteAccount(@RequestParam String id) {
        return accountService.deleteAccount(id);
    }
}
