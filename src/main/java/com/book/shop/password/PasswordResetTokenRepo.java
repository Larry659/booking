package com.book.shop.password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken,Long> {

    PasswordResetToken findPasswordResetTokenByToken(String token);

}
