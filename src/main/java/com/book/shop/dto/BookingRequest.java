package com.book.shop.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BookingRequest {

    private String name;

    private String lastName;

    private String phone;

    private LocalDateTime appointmentDate;
}
