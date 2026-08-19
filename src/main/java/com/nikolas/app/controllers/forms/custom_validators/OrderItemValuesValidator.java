package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Map;

public class OrderItemValuesValidator implements ConstraintValidator<OrderItemValuesConstraint, Map<Integer, Integer>> {
    @Override
    public void initialize(OrderItemValuesConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid( Map<Integer, Integer> order, ConstraintValidatorContext constraintValidatorContext) {
        for (Integer quantity: order.values()) {
            if (quantity < 0 || quantity > 100)
                return false;
        }
        return true;
    }
}