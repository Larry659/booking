package com.book.shop.repo;

import com.book.shop.model.Accounts;
import com.book.shop.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<Accounts, Long> {
    @Query( value = "select * from accounts where email=:email",nativeQuery = true)
    Optional<Accounts> findAccountsByEmail(String email);
    @Query(value = "SELECT * FROM accounts  WHERE MONTH(date_created) =MONTH(CURRENT_DATE())", nativeQuery = true)
    List<Accounts> fetchAllAccountsForMonth();
}
