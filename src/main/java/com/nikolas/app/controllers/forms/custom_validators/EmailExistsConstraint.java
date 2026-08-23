package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailExistsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExistsConstraint {
    String message() default "To email δεν υπάρχει";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}