package com.nikolas.app.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
public class Degree {
    @Id
    Integer id;
    String name;
}
