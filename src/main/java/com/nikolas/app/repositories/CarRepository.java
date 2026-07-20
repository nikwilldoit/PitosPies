package com.nikolas.app.repositories;

import com.nikolas.app.repositories.entities.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {

}
