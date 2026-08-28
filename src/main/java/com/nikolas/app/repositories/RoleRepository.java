package com.nikolas.app.repositories;

import com.nikolas.app.models.Role;
import com.nikolas.app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findRoleByName(String role);
}