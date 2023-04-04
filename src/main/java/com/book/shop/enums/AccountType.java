package com.book.shop.enums;

public enum AccountType {
   USER("user"),
    ADMIN("admin");

    public final String label;

    AccountType(String label) {
        this.label = label;
    }
}
