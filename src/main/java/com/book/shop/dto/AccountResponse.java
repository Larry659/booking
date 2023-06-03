package com.book.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse implements Serializable {
    private String name;
    private String lastName;
    private String username;
    private String gender;
    private String email;
    private String phone;
    private LocalDateTime dateCreated;

}
