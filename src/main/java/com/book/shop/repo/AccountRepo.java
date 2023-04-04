package com.book.shop.repo;

import com.book.shop.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepo extends JpaRepository<Accounts, UUID> {

}
