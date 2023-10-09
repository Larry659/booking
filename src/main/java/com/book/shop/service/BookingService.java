package com.book.shop.service;

import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.BookingRequest;
import org.springframework.http.ResponseEntity;

public interface BookingService {
    ApiResponse<?> addBooking(BookingRequest payload);
    ApiResponse<?> updateBooking(Long id,BookingRequest payload);

    ApiResponse<?> getBookings();
    ApiResponse<?> deleteBookings(Long id);

    ApiResponse<?> getBookingPerMonth();

    ApiResponse<?> getBookingCountPerMonth();
    ApiResponse<?> getBookingCount();

}
