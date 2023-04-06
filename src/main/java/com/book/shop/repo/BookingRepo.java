package com.book.shop.repo;

import com.book.shop.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepo extends JpaRepository< Bookings,Long> {
   // select * from test_data where month(date_created) = month(current_date())
    @Query( value = "select * from booking where month(appointment_date)=:month(current_date())",nativeQuery = true)
    List<Bookings> fetchAllBookingsForTheMonth();
}
