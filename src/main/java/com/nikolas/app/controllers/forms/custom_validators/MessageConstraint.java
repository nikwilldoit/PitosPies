package com.nikolas.app.controllers.forms.custom_validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MessageValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageConstraint {
    String message() default "Το μήνυμα πρέπει να έχει απο 5 έως 100 χαρακτήρες αυστηρά";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}