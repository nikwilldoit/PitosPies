package com.nikolas.app.repositories;

import com.nikolas.app.models.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AreaRepository extends CrudRepository<Area, Integer> {

    Area findAreaById(int id);
}
