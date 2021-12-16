package com.example.demo.controllers;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonId;
import com.example.demo.domain.PersonName;
import com.example.demo.model.CreatePersonInput;
import com.example.demo.model.CreatePersonOutput;
import com.example.demo.model.UpdatePersonInput;
import com.example.demo.model.UpdatePersonOutput;
import com.example.demo.services.PersonServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonsController {
    private final PersonServices services;

    public PersonsController(PersonServices services) {
        this.services = services;
    }


    @GetMapping
    public List<Person> listPersons() {
        return services.listPersons();
    }

    @PostMapping
    public CreatePersonOutput createPerson(@RequestBody CreatePersonInput input) {
        PersonName personName = new PersonName(input.getName());
        LocalDate birthday = input.getBirthday();
        PersonId random = PersonId.random();
        Person person = new Person(random, personName, birthday);
        Person createdPerson = services.createPerson(person);

        return new CreatePersonOutput(createdPerson);
    }


    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable("id") String personId) {
        final PersonId id = PersonId.fromString(personId);
        return services.getPerson(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable("id") String personId) {
        final PersonId id = PersonId.fromString(personId);
        services.deletePerson(id);
    }

    @PutMapping(value = "/{id}")
    public UpdatePersonOutput updatePerson(@PathVariable("id") String unsafeId, @RequestBody UpdatePersonInput input) {
        final PersonId id = PersonId.fromString(unsafeId);
        Person newPerson = new Person(id, new PersonName(input.getName()), input.getBirthday());
        final Person updated = services.updatePerson(id, newPerson);
        return new UpdatePersonOutput(updated);
    }
}
