package com.xml.parser.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AnalyseParamsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnalyseParamsConstraint {
    String message() default "Invalid AnalyseParams";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
