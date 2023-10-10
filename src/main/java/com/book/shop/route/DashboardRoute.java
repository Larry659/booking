package com.book.shop.route;


import com.book.shop.dto.ApiResponse;

import com.book.shop.service.DashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(value ="/api/v1/dashboard")

@RequiredArgsConstructor
public class DashboardRoute {
    private final DashService dashService;

//    @PostMapping("/add")
//    ResponseEntity addAccount(@RequestBody AccountRequest payload) {
//        return accountService.createAccount(payload);
//    }
    @GetMapping ("/card_values")
    ApiResponse<?> getDashboardValues() {
        return dashService.getDashCardValues();
    }

}
