package com.nikolas.app.repositories;

import com.nikolas.app.repositories.entities.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository {

    Person findPersonByLastnameAndSalary(String lastname, int salary);



}
