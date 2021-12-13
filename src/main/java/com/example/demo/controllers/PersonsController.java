package com.example.demo.controllers;

import com.example.demo.domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonsController {

    @GetMapping
    public List<Person> listPersons() {
        return List.of(
                new Person("123", "Cristian"),
                new Person("1234", "Andres")
        );
    }

    @PostMapping
    public Person createPerson(
            @RequestBody Person person
    ) {
        return person;
    }



    @GetMapping(value = "/{id}")
    public Person getPerson(
            @PathVariable("id") String personId
    ) {
        return new Person(personId, "Name: " + personId);
    }

    @DeleteMapping(value = "/{id}")
    public Person deletePerson(
            @PathVariable("id") String personId
    ) {
        return new Person(personId, "Name: " + personId);
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(
            @PathVariable("id") String personId,
            @RequestBody Person person
    ) {
        return person;
    }
}
