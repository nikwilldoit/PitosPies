package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MessageValidator implements ConstraintValidator<MessageConstraint, String> {
    @Override
    public void initialize(MessageConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>=5 && s.length()<=100;
    }
}
