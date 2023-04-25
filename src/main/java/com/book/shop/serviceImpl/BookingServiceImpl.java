package com.book.shop.serviceImpl;

import com.book.shop.dto.BookingRequest;
import com.book.shop.model.Bookings;
import com.book.shop.repo.BookingRepo;
import com.book.shop.service.BookingService;
import com.book.shop.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private  final BookingRepo bookingRepo;
    private final MailService mailService;
    @Override
    public ResponseEntity addBooking(BookingRequest payload) {
               if(!Objects.equals(payload.getLastName(), "") && !Objects.equals(payload.getName(), "") && !Objects.equals(payload.getPhone(), "") && patternMatches_(payload.getEmail())){
                   Bookings bookings = new Bookings();
                   bookings.setName(payload.getName());
                   bookings.setLastName(payload.getLastName());
                   bookings.setPhone(payload.getPhone());
                   bookings.setEmail(payload.getEmail());
                   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                   //convert String to LocalDate
                   bookings.setAppointmentDate(LocalDate.parse(payload.getAppointmentDate(),formatter));
                   log.info("The booking is {}:",bookings);
                   bookingRepo.save(bookings);
                   mailService.sendMailForBooking(payload);
                   return ResponseEntity.ok("new bookings with  saved");
               }

        else {
            return ResponseEntity.ok("Operation failed");
               }
    }


    @Override
    public ResponseEntity updateBooking(Long id,BookingRequest payload) {
       Optional<Bookings> bookingsOptional = bookingRepo.findById(id);
       bookingsOptional.ifPresent((booking)->{
           if(Objects.nonNull(booking.getName()) && !"".equalsIgnoreCase(booking.getName())){
               booking.setName(payload.getName());
           }
           if(Objects.nonNull(booking.getLastName()) && !"".equalsIgnoreCase(booking.getLastName())){
               booking.setLastName(payload.getLastName());
           }
           if(Objects.nonNull(booking.getEmail()) && !"".equalsIgnoreCase(booking.getEmail())){
               if (patternMatches(payload.getEmail()))
               booking.setEmail(payload.getEmail());
           }
           if(Objects.nonNull(booking.getPhone()) && !"".equalsIgnoreCase(booking.getPhone())){
               booking.setPhone(payload.getPhone());
           }
           if(Objects.nonNull(booking.getAppointmentDate())){
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               //convert String to LocalDate
               booking.setAppointmentDate(LocalDate.parse(payload.getAppointmentDate(),formatter));
           }
           bookingRepo.save(booking);
       });
       return ResponseEntity.ok("Booking successfully updated");
    }

    @Override
    public ResponseEntity getBookings() {
       List<Bookings> bookingsList = bookingRepo.findAll();
       return ResponseEntity.ok(bookingsList);
    }

    @Override
    public ResponseEntity deleteBookings(Long id) {
       Optional<Bookings> optionalBookings = bookingRepo.findById(id);
       optionalBookings.ifPresent((booking)->{
           bookingRepo.delete(booking);
       });
      return ResponseEntity.ok("Record deleted successfully");
    }

    @Override
    public ResponseEntity getBookingPerMonth() {
        List<Bookings> bookingsList = bookingRepo.fetchAllBookingsForTheMonth();
        return ResponseEntity.ok(bookingsList);
    }
    private static final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean patternMatches_(String s) {
        return EMAIL.matcher(s).matches();
    }
    public static boolean patternMatches(String emailAddress) {
         String regexPattern ="^(.+)@(\\S+) $";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }




}
