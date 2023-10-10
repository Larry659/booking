package com.book.shop.serviceImpl;

import com.book.shop.dto.ApiResponse;
import com.book.shop.dto.DashValue;
import com.book.shop.repo.AccountRepo;
import com.book.shop.repo.BookingRepo;
import com.book.shop.service.DashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.book.shop.dto.AppCode.ERROR_CODE;
import static com.book.shop.dto.AppCode.OKAY;
import static com.book.shop.dto.MessageUtil.FAILED;
import static com.book.shop.dto.MessageUtil.SUCCESS;

@Service
@Slf4j
@RequiredArgsConstructor
public class DashImpl implements DashService {
    private final BookingRepo bookingRepo;
    private final AccountRepo accountRepo;
    @Override
    public ApiResponse<?> getDashCardValues() {
        DashValue dashValue = DashValue.builder()
                .accountMonth(accountRepo.fetchAllAccountsForMonth().size())
                .bookMonth(bookingRepo.fetchAllBookingsForTheMonth().size())
                .totalAccount(accountRepo.findAll().size())
                .totalBook(bookingRepo.findAll().size())
                .build();
        if(dashValue!=null){
            return new ApiResponse<>(SUCCESS,OKAY,dashValue);
        }
      else   return new ApiResponse<>(FAILED,ERROR_CODE);
    }
}
