package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TelephoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TelephoneConstraint {
    String message() default "Μη έγκυρο τηλέφωνο";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}