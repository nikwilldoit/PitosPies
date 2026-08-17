package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<TelephoneConstraint, String> {
    @Override
    public void initialize(TelephoneConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isEmpty() || ((s.startsWith("2") || s.startsWith("6")) && s.length()==10);
    }
}