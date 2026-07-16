package com.nikolas.app.repositories;

import com.nikolas.app.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    //DIAFORETIKOS TROPOS GIA DEPENDENCY INJECTION ME CONSTRUCTOR
//    public PersonRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> getAllUsers() {
        String sql = "SELECT * FROM person";
        List<Person> persons = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class));

        return persons;
    }
}
