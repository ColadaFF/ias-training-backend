package com.example.demo.controllers;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonsController {
    private PersonsRepository repository;

    public PersonsController(PersonsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> listPersons() {
        return repository.list();
    }

    @PostMapping
    public Person createPerson(
            @RequestBody Person person
    ) {
        repository.create(person);

        return person;
    }


    @GetMapping(value = "/{id}")
    public Person getPerson(
            @PathVariable("id") String personId
    ) {
        return repository.findOne(personId);
    }

    @DeleteMapping(value = "/{id}")
    public Person deletePerson(
            @PathVariable("id") String personId
    ) {
        Person foundPerson = repository.findOne(personId);
        repository.delete(personId);
        return foundPerson;
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(
            @PathVariable("id") String personId,
            @RequestBody Person person
    ) {
        repository.update(personId, person);

        return repository.findOne(personId);
    }
}
