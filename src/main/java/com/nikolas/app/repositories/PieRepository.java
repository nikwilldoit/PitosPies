package com.nikolas.app.repositories;

import com.nikolas.app.models.Pie;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PieRepository extends CrudRepository<Pie, Integer> {

    Pie findPieById(int id);

    @Query("SELECT ingredient.name AS name " +
            "FROM ingredient " +
            "    JOIN pie_ingredient ON ingredient.id = pie_ingredient.ingredient_id " +
            "    JOIN pie ON pie_ingredient.pie_id = pie.id " +
            "WHERE pie.id=:pie_id")
    List<String> findIngredientsOfPie(@Param("pie_id") int id);
}
