package org.people.weijuly.bookstore.util;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum LendingStatus {
    ACTIVE("A"),
    RETURNED("R");

    private final String code;

    LendingStatus(String code) {
        this.code = code;
    }

    public static LendingStatus of(String code) {
        return Arrays.stream(LendingStatus.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BookStoreException(
                        String.format("Cannot convert code: %s into LendingStatus enum", code),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ));
    }

    public String getCode() {
        return code;
    }
}
