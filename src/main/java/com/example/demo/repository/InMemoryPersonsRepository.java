package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryPersonsRepository implements PersonsRepository {
    private final Map<String, Person> database = new HashMap<>();

    @Override
    public List<Person> list() {
        Collection<Person> values = database.values();
        return new ArrayList<>(values);
    }

    @Override
    public Person findOne(String id) {
        return database.get(id);
    }

    @Override
    public void create(Person person) {
        Person foundPerson = database.get(person.getId());
        if (foundPerson != null) {
            throw new IllegalArgumentException("Person with id: " + person.getId() + " already exists.");
        }
        database.put(person.getId(), person);
    }

    @Override
    public void update(String id, Person person) {
        Person foundPerson = database.get(id);
        if (foundPerson == null) {
            throw new IllegalArgumentException("Person with id: " + id + " not found");
        }

        database.put(id, person);
    }

    @Override
    public void delete(String id) {
        Person foundPerson = database.get(id);
        if (foundPerson == null) {
            throw new IllegalArgumentException("Person with id: " + id + " not found");
        }

        database.remove(id);

    }
}
