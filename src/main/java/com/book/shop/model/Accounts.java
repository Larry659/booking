package com.book.shop.model;

import com.book.shop.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table( name = "accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "date_created")

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreated;
@OneToOne(mappedBy = "account")
    private Bookings bookings;
}
