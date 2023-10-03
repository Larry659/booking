package com.book.shop.exception;

public class TokenException extends RuntimeException {
    private String message;

    public TokenException(String token, String message) {
    }
}
