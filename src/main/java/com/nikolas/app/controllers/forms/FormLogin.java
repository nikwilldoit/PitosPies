package com.nikolas.app.controllers.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormLogin {

    @NotNull(message="Το username πρέπει να είναι συμπληρωμένο")
    @NotEmpty(message="Το username δεν πρέπει να είναι κενό")
    private String username;

    @NotNull(message="Το password πρέπει να είναι συμπληρωμένο")
    @NotEmpty(message="Το password δεν πρέπει να είναι κενό")
    private String password;

}
