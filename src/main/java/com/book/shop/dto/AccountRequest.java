package com.book.shop.dto;

import com.book.shop.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private String accountType;
    private String role;
}
