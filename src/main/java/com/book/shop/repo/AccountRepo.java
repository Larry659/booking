package com.book.shop.repo;

import com.book.shop.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<Accounts, Long> {
    @Query( value = "select * from accounts where email=:email",nativeQuery = true)
    Optional<Accounts> findAccountsByEmail(String email);

}
