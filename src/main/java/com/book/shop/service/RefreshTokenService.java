package com.book.shop.service;

import com.book.shop.exception.TokenException;
import com.book.shop.model.RefreshToken;
import com.book.shop.repo.AccountRepo;
import com.book.shop.repo.RefreshTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${REFRESH_TIME}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    @Autowired
    private AccountRepo accountRepo;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setAccount(accountRepo.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepo
                .save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new TokenException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

//    @Transactional
//    public int deleteByAccountId(Long Id) {
//        return refreshTokenRepo.deleteByAccount(accountRepo.findById(Id).get());
//    }

}
