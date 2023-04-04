package com.book.shop.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
public class AccountResponse implements Serializable {
    private String name;

    private String lastName;

    private String gender;

    private String email;

    private String password;

    private String phone;

    private LocalDateTime dateCreated;

}
