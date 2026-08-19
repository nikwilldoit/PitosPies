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
public class FormDataOrder {

    @NotNull (message = "Το ονοματεπώνυμο πρέπει να μην είναι null")
    @NotEmpty (message = "Το ονοματεπώνυμο πρέπει να μην είναι κενό")
    private String fullname;

    private String address;

    @NotNull (message = "Το e-mail πρέπει να μην είναι null")
    @NotEmpty (message = "To e-mail πρέπει να μην είναι κενό")
    @Email (message = "Το e-mail δεν είναι έγκυρο")
    private String email;

    @TelephoneConstraint
    private String tel;

    @NotNull
    @NotEmpty
    private String comments;

    @NotNull
    @NotEmpty
    private boolean offer;

    @NotNull
    @NotEmpty
    private String payment;

}
