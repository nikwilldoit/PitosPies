package com.nikolas.app.repositories.entities;

import com.nikolas.app.models.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

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

    @MappedCollection(idColumn = "pie_id", keyColumn = "order")
    private List<Award> awards;

//    @MappedCollection(idColumn = "pie_id", keyColumn = "order")
//    private Set<Award> awards;
}
