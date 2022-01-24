package org.people.weijuly.bookstore.data;

import org.people.weijuly.bookstore.util.BookStoreException;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.http.HttpStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Optional;

@Converter
public class LendingStatusConverter implements AttributeConverter<LendingStatus, String> {

    @Override
    public String convertToDatabaseColumn(LendingStatus lendingStatus) {
        return Optional
                .ofNullable(lendingStatus)
                .map(LendingStatus::getCode)
                .orElseThrow(() -> new BookStoreException("Cannot convert null LendingStatus", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    public LendingStatus convertToEntityAttribute(String code) {
        return Arrays.stream(LendingStatus.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new BookStoreException(
                        String.format("Cannot convert code: %s into LendingStatus enum", code),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ));
    }
}
