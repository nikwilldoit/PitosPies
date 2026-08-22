package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.MessageConstraint;
import com.nikolas.app.controllers.forms.custom_validators.TelephoneConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormRegister {

    @NotNull (message = "Το ονοματεπώνυμο πρέπει να μην είναι null")
    @NotEmpty (message = "Το ονοματεπώνυμο πρέπει να μην είναι κενό")
    private String fullname;

    @NotNull (message = "Το e-mail πρέπει να μην είναι null")
    @NotEmpty (message = "To e-mail πρέπει να μην είναι κενό")
    @Email (message = "Το e-mail δεν είναι έγκυρο")
    private String email;

    @TelephoneConstraint
    private String tel;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο username (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String username;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password2;

    @AssertTrue(message = "Οι κωδικοί που δώσατε δεν είναι ίδιοι")
    private Boolean passwordsMatch;

}
