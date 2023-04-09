package com.book.shop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
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

    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "appointment_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate appointmentDate;
}
