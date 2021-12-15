package com.example.demo.model;

import com.example.demo.domain.Person;

public class UpdatePersonOutput {
    private Person person;

    public UpdatePersonOutput() {
    }

    public UpdatePersonOutput(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
