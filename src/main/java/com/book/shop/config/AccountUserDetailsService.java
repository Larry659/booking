package com.book.shop.config;

import com.book.shop.exception.RecordNotFoundException;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor

public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Accounts> acct = accountRepository.findByUserName(username);

        return acct.map(AccountUserDetails::new).orElseThrow(() -> new RecordNotFoundException(String.format("Record with username %s not found", username)));
    }


}
