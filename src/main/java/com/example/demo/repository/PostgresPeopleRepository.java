package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// @Component
public class PostgresPeopleRepository implements PersonsRepository {
    private final DataSource dataSource;

    public PostgresPeopleRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Person> list() {
        List<Person> result = new ArrayList<>();
        String sqlQuery = "select * from persons ";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
        ) {
            while (resultSet.next()) {
                String personId = resultSet.getString("id_number");
                String personName = resultSet.getString("name");
                Person person = new Person(
                        personId,
                        personName
                );
                result.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public Person findOne(String id) {
        Person person = null;

        String sqlQuery = "select * from persons where id_number = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ) {
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                String personId = resultSet.getString("id_number");
                String personName = resultSet.getString("name");
                person = new Person(personId, personName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public void create(Person person) {
        String sqlQuery = "insert into persons(id_number, name) values(?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ) {
            ps.setString(1, person.getId());
            ps.setString(2, person.getName());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, Person person) {
        String sqlQuery = "update persons set id_number = ?, name = ? where id_number = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ) {
            ps.setString(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sqlQuery = "delete from persons where id_number = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ) {
            ps.setString(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
