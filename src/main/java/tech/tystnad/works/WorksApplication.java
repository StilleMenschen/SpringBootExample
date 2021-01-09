package tech.tystnad.works;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用的入口文件
 * Created by wangpeng on 2017/1/24.
 */
@MapperScan("tech.tystnad.works.repository.mapper")
@SpringBootApplication
public class WorksApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WorksApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WorksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("http://localhost:8080/swagger-ui/");
    }
}
