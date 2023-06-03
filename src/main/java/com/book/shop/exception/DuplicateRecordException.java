package com.book.shop.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateRecordException extends RuntimeException {

    public DuplicateRecordException(String message) {
        super(message);
    }
}
