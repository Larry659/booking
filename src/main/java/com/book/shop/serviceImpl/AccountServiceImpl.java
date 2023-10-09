package com.book.shop.serviceImpl;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.AccountResponse;
import com.book.shop.dto.ApiResponse;
import com.book.shop.enums.AccountType;
import com.book.shop.exception.DuplicateRecordException;
import com.book.shop.exception.RecordNotFoundException;
import com.book.shop.mapper.Mapper;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import com.book.shop.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.book.shop.dto.AppCode.ERROR_CODE;
import static com.book.shop.dto.AppCode.OKAY;
import static com.book.shop.dto.MessageUtil.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    public static void createPasswordReset(Accounts accounts, String passwordToken) {
    }

    @Override
    public ResponseEntity<?> createAccount(AccountRequest payload) {
        Optional<Accounts> acct = accountRepo.findAccountsByEmail(payload.getEmail());
        if(acct.isEmpty()){
            Accounts account = new Accounts();
                account.setFirstName(payload.getFirstName());
            account.setLastName(payload.getLastName());
            account.setEmail(payload.getEmail());
            account.setPassword(payload.getPassword());
//            account.setGender(payload.getGender());
            account.setPhone(payload.getPhone());
            account.setAccountType(AccountType.USER);
            account.setDateCreated(LocalDateTime.now());
            account.setAppUserName(payload.getAppUserName());
            Accounts save = accountRepo.save(account);
            log.info("Saved Account: {}", save );
        }else
            throw new DuplicateRecordException("record already exist");

      return  ResponseEntity.ok("Account created successfully");

    }


    @Override
    public ApiResponse<?> updatedAccount(Long id, AccountRequest load) {
        Accounts acct = accountRepo.findById(id).get();
            if(Objects.nonNull(acct.getFirstName()) && !"".equalsIgnoreCase(acct.getFirstName())){
                acct.setFirstName(load.getFirstName());
            }
        if(Objects.nonNull(acct.getLastName()) && !"".equalsIgnoreCase(acct.getLastName() )){
            acct.setLastName(load.getLastName());
        }
        if(Objects.nonNull(acct.getEmail()) && !"".equalsIgnoreCase(acct.getEmail() )){
            acct.setEmail(load.getEmail());
        }
        if(Objects.nonNull(acct.getPhone()) && !"".equalsIgnoreCase(acct.getPhone() )){
            acct.setPhone(load.getPhone());
        }
        if(Objects.nonNull(acct.getPassword()) && !"".equalsIgnoreCase(acct.getPassword() )){
            acct.setPassword(load.getPassword());
        }
        if(Objects.nonNull(acct.getUsername()) && !"".equalsIgnoreCase(acct.getUsername() )){
            acct.setAppUserName(load.getAppUserName());
        }
      Accounts ac = accountRepo.save(acct);
        log.info("Updated value :{}",ac);
        return new ApiResponse<>(SUCCESS,OKAY,load);
    }

    @Override
    public ApiResponse<?> listAccounts() {

        List<Accounts> accountsList = accountRepo.findAll();
        if(!accountsList.isEmpty()){
            List<AccountResponse> responseList = Mapper.convertList(accountsList,AccountResponse.class);
            //List<AccountResponse> responseList = AccountConverter.convertToResponseList(accountsList);
            log.info("This is the responseList {}",responseList);
            return new ApiResponse<>(SUCCESS,OKAY,responseList);

        }
     else
         return new ApiResponse<>(FAILED,ERROR_CODE);
    }

    @Override
    public ApiResponse<?> deleteAccount(Long id) {

       Optional<Accounts> account = accountRepo.findById(id);
       if(account.isPresent()){
           accountRepo.delete(account.get());
           return new ApiResponse<>(DELETED,OKAY);

       }else
           throw new RecordNotFoundException("record with id " + account.get().getId());



//      account.ifPresentOrElse((acct)->{accountRepo.delete(acct);},
//    throw new RecordNotFoundException("record with this id not found");
//

    }

    @Override
    public ApiResponse<?> countAccounts() {
        List<Accounts> accountsList = accountRepo.findAll();
        if(!accountsList.isEmpty()){
            int totalAccount = accountsList.size();
            //List<AccountResponse> responseList = AccountConverter.convertToResponseList(accountsList)
            return new ApiResponse<>(SUCCESS,OKAY,totalAccount);

        }
        else
            return new ApiResponse<>(FAILED,ERROR_CODE);
    }

    @Override
    public ApiResponse<?> countAccountForMonth() {
        List<Accounts> accountsList = accountRepo.fetchAllAccountsForMonth();
        if(!accountsList.isEmpty()){
            int totalAccount = accountsList.size();
            //List<AccountResponse> responseList = AccountConverter.convertToResponseList(accountsList)
            return new ApiResponse<>(SUCCESS,OKAY,totalAccount);

        }
        else
            return new ApiResponse<>(FAILED,ERROR_CODE);
    }
}
