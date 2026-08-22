package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.EmailNotExistsConstraint;
import com.nikolas.app.controllers.forms.custom_validators.MessageConstraint;
import com.nikolas.app.controllers.forms.custom_validators.TelephoneConstraint;
import com.nikolas.app.controllers.forms.custom_validators.UsernameNotExistsConstraint;
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
    @EmailNotExistsConstraint(message = "Το e-mail που δώσατε χρησιμοποιείται από άλλον χρήστη. Επιλέξτε νέο!")
    private String email;

    @TelephoneConstraint
    private String tel;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο username (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    @UsernameNotExistsConstraint(message = "Το όνομα χρήστη υπάρχει ήδη. Επιλέξτε νέο!")
    private String username;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password;

    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$|^$", message = "Μη έγκυρο password (5-20 λατινικοί χαρακτήρες ή/και αριθμοί)")
    private String password2;

    @AssertTrue(message = "Οι κωδικοί που δώσατε δεν είναι ίδιοι")
    private Boolean passwordsMatch;

    public FormRegister(String fullname, String email, String tel, String username, String password, String password2) {
        this.fullname = fullname;
        this.email = email;
        this.tel = tel;
        this.username = username;
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
