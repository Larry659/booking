package com.book.shop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookingResponse implements Serializable {


    private String name;
    private String lastName;

    private String email;


    private String phone;

    private String appointmentDate;
}
