package com.nikolas.app.services;

import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.models.User;
import com.nikolas.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AuthService {

    @Autowired
    SessionBean sessionBean;

    @Autowired
    UserRepository userRepository;

    public String registerUser(String username, String password){

        String message;

        User user = userRepository.findUserByUsername(username);
        if(user!=null){
            message = "User "+ userRepository.findUserByUsername(username).getUsername() +" already exists!";
        }
        else{
            message = "User "+ username +" just registered!";
            userRepository.save(new User(null, username, password, null));
        }
        return message;
    }

    public String loginUser(String username, String password){

        String message;

        User user = userRepository.findUserByUsername(username);
        if(user!=null){
            message = "User "+ userRepository.findUserByUsernameAndPassword(username, password).getUsername() +" just logged in!";
        }
        else{
            message = "Wrong Credentials";
        }
        return message;
    }
}
