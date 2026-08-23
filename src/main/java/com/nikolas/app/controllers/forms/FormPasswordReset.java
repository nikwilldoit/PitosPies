package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.EmailExistsConstraint;
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
    @NotNull(message="Το email πρέπει να είναι συμπληρωμένο")
    @Email
    @EmailExistsConstraint(message = "Το e-mail που δώσατε δεν χρησιμοποιείται από κάποιον χρήστη!")
    private String email;
}
