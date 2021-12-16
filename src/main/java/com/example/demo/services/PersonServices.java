package com.example.demo.services;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonId;
import com.example.demo.domain.PersonName;
import com.example.demo.model.CreatePersonInput;
import com.example.demo.model.CreatePersonOutput;
import com.example.demo.model.UpdatePersonInput;
import com.example.demo.model.UpdatePersonOutput;
import com.example.demo.repository.PersonsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonServices {


    private PersonsRepository repository;

    public PersonServices(PersonsRepository repository) {
        this.repository = repository;
    }


    public List<Person> listPersons() {
        return repository.list();
    }


    public Person createPerson(Person person) {
        repository.create(person);
        return person;
    }


    public Person getPerson(PersonId personId) {
        return repository.findOne(personId);
    }


    public void deletePerson(PersonId personId) {
        repository.delete(personId);
    }


    public Person updatePerson(PersonId personId, Person person) {
        repository.update(personId, person);

        return repository.findOne(personId);
    }

}
