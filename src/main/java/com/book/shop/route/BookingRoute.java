package com.book.shop.route;

import com.book.shop.dto.BookingRequest;
import com.book.shop.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/bookings")
@RequiredArgsConstructor
public class BookingRoute {
    private final BookingService bookingService;

    @PostMapping("")
    ResponseEntity<?> addBooking(@RequestBody BookingRequest payload) {
        return bookingService.addBooking(payload);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody BookingRequest payload) {
        return bookingService.updateBooking(id, payload);
    }

    @GetMapping("")
    ResponseEntity<?> getBookings() {
        return bookingService.getBookings();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBookings(id);
    }

    @GetMapping("/booking-per-month")
    ResponseEntity<?> getBookingForThisMonth() {
        return bookingService.getBookingPerMonth();
    }
}