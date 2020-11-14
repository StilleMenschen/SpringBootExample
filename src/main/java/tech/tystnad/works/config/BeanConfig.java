package tech.tystnad.works.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tech.tystnad.works.properties.IdWorkerProperties;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class BeanConfig {
    @Resource
    private IdWorkerProperties idWorkerProperties;

    @Bean("idWorker")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IdWorker idWorker() {
        // 雪花算法的ID生成器,指定工作ID和数据中心ID,最大值为31(五位二进制数都为1,0b11111)
        return new IdWorker(idWorkerProperties.getWorkerId(), idWorkerProperties.getDataCenterId());
    }

    @Bean
    public Validator validator() {
        // 当使用@Validated注解时,只会对配置了分组校验注解的字段生效
        // 不指定分组的情况下javax.validation.groups.Default是默认的校验分组
        // 自定义错误提示(message)在资源目录下的ValidationMessages.properties这个文件中
        // 使用HibernateValidator的实现类
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) // 快速失败,遇到第一个校验不通过直接抛出异常
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
