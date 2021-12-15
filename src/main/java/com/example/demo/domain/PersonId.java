package com.example.demo.domain;

import java.util.Objects;
import java.util.UUID;

public class PersonId {
    private final UUID value;

    public PersonId(UUID value) {
        Objects.requireNonNull(value, "Person id can not be null");
        this.value = value;
    }

    public static PersonId fromString(String unsafeValue) {
        return new PersonId(UUID.fromString(unsafeValue));
    }

    public static PersonId random() {
        return new PersonId(UUID.randomUUID());
    }


    @Override
    public String toString() {
        return value.toString();
    }
}
