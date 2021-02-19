package tech.tystnad.works.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        // 在Controller中使用BindingResult也可以获取到验证错误信息
        // if (bindingResult.hasErrors()) {
        //     for (ObjectError error : bindingResult.getAllErrors()) {
        //         System.out.println(error.getDefaultMessage());
        //     }
        // }
        // 当使用@Validated注解时,只会对配置了分组校验注解的字段生效
        // 不指定分组的情况下javax.validation.groups.Default是默认的校验分组
        // 自定义错误提示(message)在资源目录下的ValidationMessages.properties这个文件中
        // 使用HibernateValidator的实现类
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(Boolean.TRUE) // 快速失败,遇到第一个校验不通过直接抛出异常
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
