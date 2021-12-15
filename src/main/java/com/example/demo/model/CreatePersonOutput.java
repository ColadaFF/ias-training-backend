package com.example.demo.model;

import com.example.demo.domain.Person;

public class CreatePersonOutput {
    private Person person;

    public CreatePersonOutput() {
    }

    public CreatePersonOutput(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
