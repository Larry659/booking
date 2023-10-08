package com.book.shop.dto;

import com.book.shop.enums.ServiceType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class BookingRequest implements Serializable {

    private String service;

    private String email;


    private String phone;

    private String appointmentDate;
}
