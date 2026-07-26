package com.nikolas.app.controllers.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDataContact {
    String fullname;
    String email;
    String tel;
    String message;

}
