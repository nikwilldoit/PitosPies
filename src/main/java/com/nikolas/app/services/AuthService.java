package com.nikolas.app.services;

import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.models.User;
import com.nikolas.app.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AuthService {

    @Autowired
    SessionBean sessionBean;

    @Autowired
    UserRepository userRepository;

    public boolean activeSession() {
        if (sessionBean.getUser()==null)
            return false;
        else if (sessionBean.getUser().getSession()==null)
            return false;

        return true;
    }

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

    public String loginUser(HttpSession session, String username, String password){
        String message;

        if (activeSession()) {
            message = "You are logged in!";
        }
        else {
            User user = userRepository.findUserByUsernameAndPassword(username, password);
            if (user != null) {
                message = "User " + userRepository.findUserByUsernameAndPassword(username, password).getUsername() + " just logged in!";
                user.setSession(session.getId());

                userRepository.save(user);
                sessionBean.setUser(user);
            }
            else {
                message = "Wrong Credentials";
            }
        }
        return message;
    }

    public String logoutUser() {
        String message;

        if (!activeSession()) {
            message = "You are not logged in!";
        }
        else {
            User user = sessionBean.getUser();
            user.setSession(null);
            userRepository.save(user);
            message = "You logged out!";
        }

        return message;
    }
}
