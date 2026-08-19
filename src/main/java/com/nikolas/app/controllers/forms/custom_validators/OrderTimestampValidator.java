package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class OrderTimestampValidator implements ConstraintValidator<OrderTimestampConstraint, LocalDateTime> {
    @Override
    public void initialize(OrderTimestampConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime timestamp, ConstraintValidatorContext constraintValidatorContext) {
        return true;
        // this code is to accept timestamps 18.00 ... 22.00
        /*
        if (timestamp.getHour()<18 || timestamp.getHour()>=23)
            return false;
        else if (timestamp.getHour()==22)
                if (timestamp.getMinute()>0 || timestamp.getSecond()>0 || timestamp.getNano()>0)
                    return false;
        return true;
         */
    }
}