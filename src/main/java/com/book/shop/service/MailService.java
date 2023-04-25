package com.book.shop.service;

import com.book.shop.dto.BookingRequest;

public interface MailService {
    void sendMailTest();
    void sendMailForBooking(BookingRequest payload);
}
