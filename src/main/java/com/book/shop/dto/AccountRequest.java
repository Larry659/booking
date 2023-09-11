package com.book.shop.dto;

import com.book.shop.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class AccountRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String appUserName;

}
