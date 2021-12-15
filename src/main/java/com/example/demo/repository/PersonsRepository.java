package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonId;

import java.util.List;

public interface PersonsRepository {
    List<Person> list();

    Person findOne(PersonId id);

    void create(Person person);

    void update(PersonId id, Person person);

    void delete(PersonId id);
}
