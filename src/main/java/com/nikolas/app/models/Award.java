package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("award")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award {
    @Id
    Integer id;
    String name;

    @Override
    public String toString() {
        return name;
    }
}
