package com.book.shop.enums;

public enum ServiceType {
   NIN("nin"),
    BVN("bvn"),
    BOTH("both");

    public final String label;

    ServiceType(String label) {
        this.label = label;
    }
}
