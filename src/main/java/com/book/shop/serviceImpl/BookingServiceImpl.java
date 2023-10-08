package com.book.shop.serviceImpl;

import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.BookingRequest;
import com.book.shop.dto.BookingResponse;
import com.book.shop.mapper.Mapper;
import com.book.shop.model.Accounts;
import com.book.shop.model.Bookings;
import com.book.shop.repo.AccountRepo;
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

import static com.book.shop.dto.AppCode.ERROR_CODE;
import static com.book.shop.dto.AppCode.OKAY;
import static com.book.shop.dto.MessageUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private  final BookingRepo bookingRepo;
    private final MailService mailService;
    private final AccountRepo accountRepo;
    @Override
    public ApiResponse<?> addBooking(BookingRequest payload) {
        Accounts acct =accountRepo.findAccountsByEmail(payload.getEmail()).get();
               if(!Objects.equals(acct.getLastName(), "") && !Objects.equals(acct.getFirstName(), "") && !Objects.equals(payload.getPhone(), "") && patternMatches_(payload.getEmail())){
                   Bookings bookings = new Bookings();
                   bookings.setName(acct.getFirstName());
                   bookings.setLastName(acct.getLastName());
                   bookings.setPhone(acct.getPhone());
                   bookings.setEmail(payload.getEmail());
                   bookings.setService(payload.getService());
                   bookings.setAccount(acct);
                 //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                   //convert String to LocalDate
                   bookings.setAppointmentDate(LocalDate.parse(payload.getAppointmentDate()));
                   log.info("The booking is {}:",bookings);
                   bookingRepo.save(bookings);
                   mailService.sendMailForBooking(payload);
                   return new ApiResponse<>(SUCCESS,OKAY,payload);
               }

        else {
            return new ApiResponse<>(FAILED,ERROR_CODE);
               }
    }


    @Override
    public ApiResponse<?> updateBooking(Long id,BookingRequest payload) {
       Optional<Bookings> bookingsOptional = bookingRepo.findById(id);
       bookingsOptional.ifPresent((booking)->{
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
       return new ApiResponse<>(SUCCESS,OKAY,payload);
    }

    @Override
    public ApiResponse<?> getBookings() {
       List<Bookings> bookingsList = bookingRepo.findAll();
       if (!bookingsList.isEmpty()){
           List<BookingResponse> responseList = Mapper.convertList(bookingsList,BookingResponse.class);
           log.info("This is the list of booking {}",responseList);
           return new ApiResponse<>(SUCCESS,OKAY,responseList);
       }
     else
         return new ApiResponse<>(FAILED,ERROR_CODE);
    }

    @Override
    public ApiResponse<?>deleteBookings(Long id) {
       Optional<Bookings> optionalBookings = bookingRepo.findById(id);
       optionalBookings.ifPresent(bookingRepo::delete);
      return new ApiResponse<>(DELETED,OKAY,optionalBookings);
    }

    @Override
    public ApiResponse<?>getBookingPerMonth() {
        List<Bookings> bookingsList = bookingRepo.fetchAllBookingsForTheMonth();
        List<BookingResponse> responseList = Mapper.convertList(bookingsList,BookingResponse.class);
        return new ApiResponse<>(SUCCESS,OKAY,responseList);
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
