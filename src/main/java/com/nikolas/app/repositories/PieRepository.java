package com.nikolas.app.repositories;

import com.nikolas.app.models.Pie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieRepository extends CrudRepository<Pie, Integer> {

    Pie findPiesById(int id);

}
