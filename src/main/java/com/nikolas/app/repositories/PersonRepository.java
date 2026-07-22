package com.nikolas.app.repositories;

import com.nikolas.app.repositories.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository {

    Person findPersonByLastnameAndSalary(String lastname, int salary);

    List<Person> findPersonByOrderByLastnameDesc(String lastname);

    Person findTopBy(int limit);



}
