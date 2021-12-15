package com.example.demo.controllers;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonId;
import com.example.demo.domain.PersonName;
import com.example.demo.model.CreatePersonInput;
import com.example.demo.model.CreatePersonOutput;
import com.example.demo.model.UpdatePersonInput;
import com.example.demo.model.UpdatePersonOutput;
import com.example.demo.repository.PersonsRepository;
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
    public CreatePersonOutput createPerson(
            @RequestBody CreatePersonInput input
    ) {

        Person person = new Person(
                PersonId.random(),
                new PersonName(input.getName()),
                input.getBirthday()
        );
        repository.create(person);

        return new CreatePersonOutput(person);
    }


    @GetMapping(value = "/{id}")
    public Person getPerson(
            @PathVariable("id") String personId
    ) {
        final PersonId id = PersonId.fromString(personId);
        return repository.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(
            @PathVariable("id") String personId
    ) {
        final PersonId id = PersonId.fromString(personId);
        repository.delete(id);
    }

    @PutMapping(value = "/{id}")
    public UpdatePersonOutput updatePerson(
            @PathVariable("id") String unsafeId,
            @RequestBody UpdatePersonInput input
    ) {
        final PersonId id = PersonId.fromString(unsafeId);
        Person newPerson = new Person(
                id,
                new PersonName(input.getName()),
                input.getBirthday()
        );
        repository.update(id, newPerson);

        final Person found = repository.findOne(id);
        return new UpdatePersonOutput(found);
    }
}
