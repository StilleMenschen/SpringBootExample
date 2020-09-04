package tech.tystnad.works.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tech.tystnad.works.util.IdWorker;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class BeanConfig {
    @Value("${server.workerId}")
    private long workerId;
    @Value("${server.dataCenterId}")
    private long dataCenterId;

    @Bean("idWorker")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IdWorker idWorker() {
        return new IdWorker(workerId, dataCenterId);
    }

    @Bean
    public Validator validator() {
        // 当使用@Validated注解时,只会对配置了分组校验注解的字段生效
        // 不指定分组的情况下javax.validation.groups.Default是默认的校验分组
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
