package com.book.shop.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum ServiceType {
   NIN("nin"),
    BVN("bvn"),
    BOTH("both");

    public final String label;

    ServiceType(String label) {
        this.label = label;
    }
    @JsonCreator
    public static ServiceType forValue(String value) {
        return Stream.of(ServiceType.values())
                .filter(enumValue -> enumValue.name().equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
