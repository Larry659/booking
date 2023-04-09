package com.book.shop.exception;

public class DuplicateRecordException extends RuntimeException {
    private String message;

    public DuplicateRecordException(String record_already_exist) {
    }
}
