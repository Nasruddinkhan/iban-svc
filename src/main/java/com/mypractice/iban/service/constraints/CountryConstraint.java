package com.mypractice.iban.service.constraints;

import com.mypractice.iban.service.validator.CountriesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = { CountriesValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryConstraint {
    String message() default "passing invalid country number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
