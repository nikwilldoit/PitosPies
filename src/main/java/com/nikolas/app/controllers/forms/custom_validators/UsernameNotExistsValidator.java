package com.nikolas.app.controllers.forms.custom_validators;

import com.nikolas.app.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameNotExistsValidator implements ConstraintValidator<UsernameNotExistsConstraint, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UsernameNotExistsConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(userRepository.findUserByUsername(username));
        return userRepository.findUserByUsername(username)==null;
    }
}