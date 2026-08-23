package com.nikolas.app.controllers.forms.custom_validators;

import com.nikolas.app.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistsValidator implements ConstraintValidator<EmailExistsConstraint, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(EmailExistsConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findUserByEmail(email)!=null;
    }
}