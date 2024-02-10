package io.innodev.blogapp.config;

import jakarta.validation.Constraint;

import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@Min(value = 1, message = "YourVariable must be 1")
@Max(value = 2, message = "YourVariable must be 2")
public @interface ValidValues {
    String message() default "Invalid value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

