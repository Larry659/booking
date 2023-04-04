package com.book.shop.repo;

import com.book.shop.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository< Bookings,Long> {
}
