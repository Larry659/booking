package com.book.shop.repo;

import com.book.shop.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Accounts, String> {
    @Query( value = "select * from accounts where email=:email",nativeQuery = true)
    Optional<Accounts> findAccountsByEmail(String email);

    Optional<Accounts> findByUserName(String username);
}
