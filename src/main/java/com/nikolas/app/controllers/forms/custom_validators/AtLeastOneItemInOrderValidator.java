package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;

public class AtLeastOneItemInOrderValidator implements ConstraintValidator<AtLeastOneItemInOrderConstraint, Map<Integer, Integer>> {
    @Override
    public void initialize(AtLeastOneItemInOrderConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid( Map<Integer, Integer> order, ConstraintValidatorContext constraintValidatorContext) {
        for (Integer quantity: order.values()) {
            if (quantity>0) return true;
        }

        return false;
    }
}