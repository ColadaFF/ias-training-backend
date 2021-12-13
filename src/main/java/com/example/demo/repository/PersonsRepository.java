package com.example.demo.repository;

import com.example.demo.domain.Person;

import java.util.List;

public interface PersonsRepository {
    List<Person> list();

    Person findOne(String id);

    void create(Person person);

    void update(String id, Person person);

    void delete(String id);
}
