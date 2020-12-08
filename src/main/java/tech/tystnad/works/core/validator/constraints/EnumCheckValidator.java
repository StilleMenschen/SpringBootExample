package tech.tystnad.works.core.validator.constraints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tystnad.works.core.validator.annotation.EnumCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumCheckValidator implements ConstraintValidator<EnumCheck, Byte> {

    private static final Logger logger = LoggerFactory.getLogger(EnumCheckValidator.class);

    /**
     * 由于使用byte类型来映射数据库的枚举类数据,此处也使用byte类型来校验
     */
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
        // 检查数据是否在指定的枚举列表中
        for (Byte e : enums) {
            if (e.equals(value)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
