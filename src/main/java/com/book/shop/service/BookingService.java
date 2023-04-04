package com.book.shop.service;

import com.book.shop.dto.BookingRequest;
import org.springframework.http.ResponseEntity;

public interface BookingService {
    ResponseEntity addBooking(BookingRequest payload);
}
