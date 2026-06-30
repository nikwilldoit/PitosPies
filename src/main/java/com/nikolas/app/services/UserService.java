package com.nikolas.app.services;

import com.nikolas.app.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUser(int id){
        if(id==1) {
            return new User(1,"Jim");
        }
        else if(id==2) {
            return new User(2,"Nikos");
        }
        return null;
    }
}
