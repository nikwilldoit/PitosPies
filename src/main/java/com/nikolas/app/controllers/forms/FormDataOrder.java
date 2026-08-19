package com.nikolas.app.controllers.forms;

import com.nikolas.app.controllers.forms.custom_validators.AtLeastOneItemInOrderConstraint;
import com.nikolas.app.controllers.forms.custom_validators.OrderItemValuesConstraint;
import com.nikolas.app.controllers.forms.custom_validators.OrderTimestampConstraint;
import com.nikolas.app.controllers.forms.custom_validators.TelephoneConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDataOrder {

    @NotNull (message = "Το ονοματεπώνυμο πρέπει να μην είναι null")
    @NotEmpty (message = "Το ονοματεπώνυμο πρέπει να μην είναι κενό")
    private String fullname;

    @NotNull (message = "Η διεύθυνση πρέπει να μην είναι null")
    @NotEmpty (message = "Η διεύθυνση πρέπει να μην είναι κενή")
    private String address;

    private Integer areaId;

    @NotNull (message = "Το e-mail πρέπει να μην είναι null")
    @NotEmpty (message = "To e-mail πρέπει να μην είναι κενό")
    @Email (message = "Το e-mail δεν είναι έγκυρο")
    private String email;

    @TelephoneConstraint
    private String tel;

    @NotNull
    @NotEmpty
    private String comments;

    @AtLeastOneItemInOrderConstraint(message="Πρέπει να παραγγείλετε τουλάχιστον μία πίτα")
    @OrderItemValuesConstraint(message="Μπορείτε να παραγγείλετε από 0-100 από κάθε είδος πίτας")
    private Map<Integer, Integer> order;

    @NotNull
    private boolean offer;

    @NotNull
    private String payment;

    @OrderTimestampConstraint(message = "Η παραγγελία μπορεί να γίνει μόνο από 18:00 έως 22:00")
    LocalDateTime stamp;

}
