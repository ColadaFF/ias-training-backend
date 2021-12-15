package com.example.demo.repository;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonId;
import com.example.demo.domain.PersonName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public class SpringJdbcPersonsRepository implements PersonsRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcPersonsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Person> rowMapper = (resultSet, rowNum) -> {
        PersonId personId = PersonId.fromString(
                resultSet.getString("id_number")
        );
        PersonName personName = new PersonName(resultSet.getString("name"));

        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();

        return new Person(
                personId,
                personName,
                birthday
        );
    };


    @Override
    public List<Person> list() {
        String sqlQuery = "select * from persons ";
        return jdbcTemplate.query(sqlQuery, rowMapper);
    }

    @Override
    public Person findOne(PersonId id) {
        String sqlQuery = "select * from persons where id_number = ?";

        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, id.toString());
    }

    @Override
    public void create(Person person) {
        String sqlQuery = "insert into persons(id_number, name, birthday) values(?, ?, ?)";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, person.getId().toString());
            ps.setString(2, person.getName().toString());
            ps.setDate(3, Date.valueOf(person.getBirthday()));
        });
    }

    @Override
    public void update(PersonId id, Person person) {
        String sqlQuery = "update persons set birthday = ?, name = ? where id_number = ?";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setDate(1, Date.valueOf(person.getBirthday()));
            ps.setString(2, person.getName().toString());
            ps.setString(3, id.toString());
        });
    }

    @Override
    public void delete(PersonId id) {
        String sqlQuery = "delete from persons where id_number = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
