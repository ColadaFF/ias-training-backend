package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private final PersonId id;
    private final PersonName name;
    private final LocalDate birthday;

    public Person(PersonId id, PersonName name, LocalDate birthday) {
        Objects.requireNonNull(id, "Person id can not be null");
        Objects.requireNonNull(name, "Person name can not be null");
        Objects.requireNonNull(birthday, "Person birthday can not be null");
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public PersonId getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
