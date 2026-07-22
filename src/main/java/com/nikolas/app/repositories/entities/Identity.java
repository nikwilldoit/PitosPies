package com.nikolas.app.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identity {
    @Id
    private Integer id;
    private String ssc;
    private String passport;
}
