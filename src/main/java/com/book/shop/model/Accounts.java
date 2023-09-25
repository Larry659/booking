package com.book.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @NotEmpty
    @Min(value = '2')
    private String firstName;

    @NotBlank
    @NotEmpty
    @Min(value = '2')
    private String lastName;

    @NotBlank
    @NotEmpty
    private String gender;

    @Positive
    @NotBlank
    @NotEmpty
    private Integer age;

    @NotBlank
    @NotEmpty
    @Column(unique = true)
    @Min(value = '5')
    private String username;

    @Email
    @NotEmpty
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotBlank
    @Min(value = '8')
    private String password;

    @NotEmpty
    @NotBlank
    private String phone;

    private String roles;
    @NotEmpty
    @NotBlank
    private String accountType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreated;

    @OneToOne(mappedBy = "account")
    private Bookings bookings;
}
