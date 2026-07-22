package com.nikolas.app.repositories;

import com.nikolas.app.repositories.entities.Person;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findPersonByLastnameAndSalary(String lastname, int salary);

    List<Person> findPersonByOrderByLastnameDesc();

    Person findTopBy(int limit);

    @Query("select * from person")
    List<Person> customQuery();

    @Query("select * from person where salary<= :salary")
    List<Person> customQuery2(@Param("salary") int salary);


}
