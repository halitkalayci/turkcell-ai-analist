package com.turkcell.productservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
@Documented
public @interface ValidCurrency {

    String message() default "Para birimi sadece ₺ veya $ olabilir";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
