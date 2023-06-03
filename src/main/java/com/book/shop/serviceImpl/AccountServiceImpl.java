package com.book.shop.serviceImpl;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.AccountResponse;
import com.book.shop.enums.AccountType;
import com.book.shop.exception.DuplicateRecordException;
import com.book.shop.exception.RecordNotFoundException;
import com.book.shop.mapper.Mapper;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepository;
import com.book.shop.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public ResponseEntity<?> createAccount(AccountRequest payload) {
        Optional<Accounts> acct = accountRepository.findAccountsByEmail(payload.getEmail());
        if(acct.isEmpty()&& acct.isEmpty()){
            Accounts account = new Accounts();
                account.setFirstName(payload.getFirstName());
            account.setLastName(payload.getLastName());
            account.setEmail(payload.getEmail());
            account.setPassword(payload.getPassword());
            account.setGender(payload.getGender());
            account.setPhone(payload.getPhone());
            account.setAccountType(AccountType.USER.label);
            account.setDateCreated(LocalDateTime.now());
            Accounts save = accountRepository.save(account);
            log.info("Saved Account: {}", save );
        }else
            throw new DuplicateRecordException("record already exist");

      return  ResponseEntity.ok("Account created successfully");

    }


    @Override
    public ResponseEntity<?> updatedAccount(String uuid,AccountRequest load) {
        Accounts acct = accountRepository.findById(uuid).get();
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
      Accounts ac = accountRepository.save(acct);
        log.info("Updated value :{}",ac);
        return ResponseEntity.ok("Account updated successfully");
    }

    @Override
    public ResponseEntity<?> listAccounts() {

        List<Accounts> accountsList = accountRepository.findAll();
        List<AccountResponse> responseList = Mapper.convertList(accountsList,AccountResponse.class);
        return ResponseEntity.ok().body(responseList);

    }

    @Override
    public ResponseEntity<?> deleteAccount(String uuid) {

       Optional<Accounts> account = accountRepository.findById(uuid);
       if(account.isPresent()){
           accountRepository.delete(account.get());
           return ResponseEntity.ok("record deleted successfully");
       }else
           throw new RecordNotFoundException("record with id " + account.get().getId());
    }
}
