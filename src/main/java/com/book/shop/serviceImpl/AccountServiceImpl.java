package com.book.shop.serviceImpl;

import com.book.shop.dto.AccountRequest;
import com.book.shop.dto.AccountResponse;
import com.book.shop.enums.AccountType;
import com.book.shop.exception.DuplicateRecordException;
import com.book.shop.mapper.Mapper;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import com.book.shop.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    @Override
    public ResponseEntity createAccount(AccountRequest payload) {
        Optional<Accounts> acct = accountRepo.findAccountsByEmail(payload.getEmail());
        if(acct.isEmpty()&& !acct.isPresent()){
            Accounts account = new Accounts();
                account.setName(payload.getName());
            account.setLastName(payload.getLastName());
            account.setEmail(payload.getEmail());
            account.setPassword(payload.getPassword());
            account.setGender(payload.getGender());
            account.setPhone(payload.getPhone());
            account.setAccountType(AccountType.USER.label);
            account.setDateCreated(LocalDateTime.now());
            Accounts save = accountRepo.save(account);
            log.info("Saved Account: {}", save );
        }else
            throw new DuplicateRecordException("record already exist");

      return  ResponseEntity.ok("Account created successfully");

    }


    @Override
    public ResponseEntity updatedAccount(String uuid,AccountRequest load) {
        Accounts acct = accountRepo.findById(uuid).get();
            if(Objects.nonNull(acct.getName()) && !"".equalsIgnoreCase(acct.getName())){
                acct.setName(load.getName());
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
      Accounts ac = accountRepo.save(acct);
        log.info("Updated value :{}",ac);
        return ResponseEntity.ok("Account updated successfully");
    }

    @Override
    public ResponseEntity listAccounts() {

        List<Accounts> accountsList = accountRepo.findAll();
        List<AccountResponse> responseList = Mapper.convertList(accountsList,AccountResponse.class);
        return ResponseEntity.ok().body(responseList);

    }

    @Override
    public ResponseEntity deleteAccount(String uuid) {

       Optional<Accounts> account = accountRepo.findById(uuid);
       accountRepo.delete(account.get());
       return ResponseEntity.ok("Successfully deleted");
    }
}
