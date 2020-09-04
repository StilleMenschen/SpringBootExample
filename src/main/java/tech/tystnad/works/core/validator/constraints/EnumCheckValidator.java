package tech.tystnad.works.core.validator.constraints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tystnad.works.core.validator.annotation.EnumCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumCheckValidator implements ConstraintValidator<EnumCheck, Byte> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private byte[] enums;

    @Override
    public void initialize(EnumCheck constraintAnnotation) {
        this.enums = constraintAnnotation.enums();
        logger.info("{}", enums);
    }

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        if (value == null) {
            return Boolean.FALSE;
        }
        logger.info("{}", value);
        for (Byte e : enums) {
            if (e.equals(value)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
