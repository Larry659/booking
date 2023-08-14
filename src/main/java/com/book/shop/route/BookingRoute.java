package com.book.shop.route;

import com.book.shop.dto.BookingRequest;
import com.book.shop.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/api/v1/booking")
@RequiredArgsConstructor
public class BookingRoute {
    private final BookingService bookingService;

    @PostMapping("/add")
    ResponseEntity addBooking(@RequestBody BookingRequest payload) {
        return bookingService.addBooking(payload);
    }

    @PostMapping("/update")
    ResponseEntity updateBooking(@RequestParam Long id, @RequestBody BookingRequest payload) {
        return bookingService.updateBooking(id, payload);
    }

    @GetMapping("/fetch")
    ResponseEntity getBookings() {
        return bookingService.getBookings();
    }

    @DeleteMapping("/delete")
    ResponseEntity deleteBooking(Long id) {
        return bookingService.deleteBookings(id);
    }

    @GetMapping("/booking-perMonth")
    ResponseEntity getBookingForThisMonth() {
        return bookingService.getBookingPerMonth();
    }
}