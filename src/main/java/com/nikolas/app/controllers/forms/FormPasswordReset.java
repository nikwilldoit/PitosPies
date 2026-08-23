package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.EmailNotExistsConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormPasswordReset {

    @NotNull(message = "Το e-mail πρέπει να μην είναι null")
    @NotEmpty(message = "To e-mail πρέπει να μην είναι κενό")
    @Email(message = "Το e-mail δεν είναι έγκυρο")
    @EmailNotExistsConstraint(message = "Το e-mail που δώσατε χρησιμοποιείται από άλλον χρήστη. Επιλέξτε νέο!")
    private String email;
}
