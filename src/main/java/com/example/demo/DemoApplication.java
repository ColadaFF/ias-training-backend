package com.example.demo;

import com.example.demo.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);



		Person person = new Person("123", "Cristian");
		Person p2 = new Person();
	}

}
