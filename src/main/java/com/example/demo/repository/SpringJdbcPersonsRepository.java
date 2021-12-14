package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class SpringJdbcPersonsRepository implements PersonsRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcPersonsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Person> rowMapper = (resultSet, rowNum) -> {
        String personId = resultSet.getString("id_number");
        String personName = resultSet.getString("name");
        return new Person(
                personId,
                personName
        );
    };


    @Override
    public List<Person> list() {
        String sqlQuery = "select * from persons ";
        return jdbcTemplate.query(sqlQuery, rowMapper);
    }

    @Override
    public Person findOne(String id) {
        String sqlQuery = "select * from persons where id_number = ?";

        return jdbcTemplate.queryForObject(sqlQuery, rowMapper, id);
    }

    @Override
    public void create(Person person) {
        String sqlQuery = "insert into persons(id_number, name) values(?, ?)";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, person.getId());
            ps.setString(2, person.getName());
        });
    }

    @Override
    public void update(String id, Person person) {
        String sqlQuery = "update persons set id_number = ?, name = ? where id_number = ?";
        jdbcTemplate.update(sqlQuery, ps -> {
            ps.setString(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, id);
        });
    }

    @Override
    public void delete(String id) {
        String sqlQuery = "delete from persons where id_number = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
