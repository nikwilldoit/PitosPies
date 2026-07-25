package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("user")
@Data
@AllArgsConstructor
public class User {
    @Id
    Integer id;
    String username;
    String password;
    String session;
}
