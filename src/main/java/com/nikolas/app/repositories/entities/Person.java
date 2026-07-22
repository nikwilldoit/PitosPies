package com.nikolas.app.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    Integer id;
    String firstname;
    String lastname;
    Integer salary;

    @Column("id")
    Identity identity;

    @MappedCollection(idColumn = "person_id")
    private Set<Car> cars;

    @MappedCollection(idColumn = "person_id", keyColumn = "order")
    private Set<Degree> degrees;

}
