package com.book.shop.password;

import com.book.shop.exception.TokenException;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepo passwordResetTokenRepo;
    private final AccountRepo accountRepo;
    public void createPasswordReset(Accounts acct, String token){
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    passwordResetToken.setToken(token);
    passwordResetToken.setAccount(acct);
    passwordResetTokenRepo.save(passwordResetToken);
    }

    public String validateToken(String token){
       PasswordResetToken accountTokenReset = passwordResetTokenRepo.findPasswordResetTokenByToken(token);
       if(accountTokenReset == null){
           throw new TokenException(token,"Invalid token");
       }
       Accounts acct = accountTokenReset.getAccount();
        Calendar calendar = Calendar.getInstance();
        if (accountTokenReset.getExpiryDate().getTime()-calendar.getTime().getTime()<=0){
            return " Link already expired. \n Resend link";
        }
        return "Valid";
    }
    public Optional<Accounts> findUserByPasswordToken(String token){
    return Optional.ofNullable(passwordResetTokenRepo.findPasswordResetTokenByToken(token).getAccount());
    }
}
