package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    Long id;
    String firstname;
    String lastname;
    Integer salary;

}
