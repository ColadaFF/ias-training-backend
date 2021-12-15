package com.example.demo.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class PersonName {
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z\\s:]{10,64}$");

    private final String value;

    public PersonName(String value) {
        Objects.requireNonNull(value, "Person name can not be null");
        String trimmedValue = value.trim();
        if(trimmedValue.length()  == 0) {
            throw new IllegalArgumentException("Person name can not be empty");
        }

        boolean isValid = pattern.matcher(trimmedValue).matches();
        if(!isValid) {
            throw new IllegalArgumentException("Invalid person name");
        }

        this.value = trimmedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
