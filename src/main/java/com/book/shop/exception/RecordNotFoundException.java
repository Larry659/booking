package com.book.shop.exception;

public class RecordNotFoundException extends RuntimeException {
    private String message;

    public RecordNotFoundException(String record_already_exist) {
    }
}
