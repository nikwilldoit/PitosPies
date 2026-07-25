package com.nikolas.app.beans;

import com.nikolas.app.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SessionBean {

    private User user;
    public SessionBean() {
    }

}
