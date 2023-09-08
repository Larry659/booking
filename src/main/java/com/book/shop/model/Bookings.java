package com.book.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "booking")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
//    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
//    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "appointment_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate appointmentDate;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Accounts account;
}
