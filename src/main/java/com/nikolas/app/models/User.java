package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    int id;
    String username;
    String password;

    @Transient
    private List<String> roles;
}
