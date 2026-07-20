package com.nikolas.app.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table
@Data
@AllArgsConstructor
public class Car {
    @Id
    Integer id;
    String name;
}
