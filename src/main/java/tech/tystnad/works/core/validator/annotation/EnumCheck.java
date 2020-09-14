package tech.tystnad.works.core.validator.annotation;

import tech.tystnad.works.core.validator.constraints.EnumCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类的数据检查,通过Constraint注解指定EnumCheckValidator为校验器
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumCheckValidator.class)
public @interface EnumCheck {
    String message() default "Parameter does not match the enumeration value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    byte[] enums() default {};
}
