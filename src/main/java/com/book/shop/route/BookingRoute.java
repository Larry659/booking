package com.book.shop.route;

import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.BookingRequest;
import com.book.shop.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/api/v1/booking")
@RequiredArgsConstructor
public class BookingRoute {
    private final BookingService bookingService;

    @PostMapping("/add")
    ApiResponse<?> addBooking(@RequestBody BookingRequest payload) {
        return bookingService.addBooking(payload);
    }

    @PostMapping("/update")
    ApiResponse<?> updateBooking(@RequestParam Long id, @RequestBody BookingRequest payload) {
        return bookingService.updateBooking(id, payload);
    }

    @GetMapping("/fetch")
    ApiResponse<?> getBookings() {
        return bookingService.getBookings();
    }

    @DeleteMapping("/delete")
    ApiResponse<?> deleteBooking(Long id) {
        return bookingService.deleteBookings(id);
    }

    @GetMapping("/booking-perMonth")
    ApiResponse<?> getBookingForThisMonth() {
        return bookingService.getBookingPerMonth();
    }

    @GetMapping("/count")
    ApiResponse<?> getBookingCount() {
        return bookingService.getBookingCount();
    }

    @GetMapping("/count_month")
    ApiResponse<?> getBookingCountForMonth() {
        return bookingService.getBookingCountPerMonth();
    }
}