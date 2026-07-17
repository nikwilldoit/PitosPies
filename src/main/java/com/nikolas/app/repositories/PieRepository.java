package com.nikolas.app.repositories;

import com.nikolas.app.models.Person;
import com.nikolas.app.models.Pie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Pie> fildAll() {
        String sql = "SELECT * FROM pie";
        List<Pie> pies = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pie.class));

        return pies;
    }


    public Pie getPieById(int id) {

        String sql = "SELECT * FROM pie WHERE id=" + id;

        List<Pie> pieEntities = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pie.class));
        return pieEntities.get(0);
    }
}
