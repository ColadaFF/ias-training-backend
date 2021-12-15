package com.example.demo.model;

import java.time.LocalDate;

public class UpdatePersonInput {
    private String name;
    private LocalDate birthday;


    public UpdatePersonInput() {
    }

    public UpdatePersonInput(String name, LocalDate birthday) {

        this.name = name;
        this.birthday = birthday;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
