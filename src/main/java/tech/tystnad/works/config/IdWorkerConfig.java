package tech.tystnad.works.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tech.tystnad.works.properties.IdWorkerProperties;
import tech.tystnad.works.util.IdWorker;

import javax.annotation.Resource;

@Configuration
public class IdWorkerConfig {
    @Resource
    private IdWorkerProperties idWorkerProperties;

    @Bean("idWorker")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IdWorker idWorker() {
        // 雪花算法的ID生成器,指定工作ID和数据中心ID,最大值为31(五位二进制数都为1,0b11111)
        return new IdWorker(idWorkerProperties.getWorkerId(), idWorkerProperties.getDataCenterId());
    }
}
