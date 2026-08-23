package com.nikolas.app.controllers.forms;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormPasswordReset2 {

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password2;
    private String code;

    @AssertTrue(message = "Οι κωδικοί που δώσατε δεν είναι ίδιοι")
    private Boolean passwordsMatch;

    public FormPasswordReset2(String password, String password2) {
        this.password = password;
        this.password2 = password2;
        this.passwordsMatch = password.equals(password2);
    }

    public void setPassword(String password) {
        this.password = password;
        this.passwordsMatch = password.equals(password2);
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
        this.passwordsMatch = password.equals(password2);
    }
}
