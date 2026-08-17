package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.TelephoneConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDataContact {
    @NotNull (message = "Το ονοματεπώνυμο πρέπει να μην είναι null")
    @NotEmpty (message = "Το ονοματεπώνυμο πρέπει να μην είναι κενό")
    private String fullname;

    @NotNull (message = "Το e-mail πρέπει να μην είναι null")
    @NotEmpty (message = "To e-mail πρέπει να μην είναι κενό")
    @Email (message = "Το e-mail δεν είναι έγκυρο")
    private String email;

    @TelephoneConstraint
    String tel;

    @NotNull
    @NotEmpty
    @Min(5)
    @Max(100)
    @Size(min = 5, max = 100, message = "Το μήνυμα πρέπει να περιέχει τουλάχιστον 5 χαρακτήρες")
    String message;

}
