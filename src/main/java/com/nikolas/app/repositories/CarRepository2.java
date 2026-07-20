package com.nikolas.app.repositories;

import com.nikolas.app.repositories.entities.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository2 extends CrudRepository<Car, Integer> {

    Car findCarByName(String name);

    List<Car> findCarByNameLike(String name);

}
