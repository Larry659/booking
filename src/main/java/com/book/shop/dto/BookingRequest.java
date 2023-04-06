package com.book.shop.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class BookingRequest implements Serializable {

    private String name;

    private String email;
    private String lastName;

    private String phone;

    private String appointmentDate;
}
