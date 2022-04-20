package com.samperry.mayparker.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {

    String message() default "{UsernameUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
