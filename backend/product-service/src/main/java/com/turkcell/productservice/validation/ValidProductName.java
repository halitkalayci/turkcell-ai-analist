package com.turkcell.productservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductNameValidator.class)
@Documented
public @interface ValidProductName {

    String message() default "İsim sadece boşluklardan oluşamaz";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
