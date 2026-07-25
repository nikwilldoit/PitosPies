package com.nikolas.app.repositories;

import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.models.Pie;
import com.nikolas.app.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserBySession(String session);
}
