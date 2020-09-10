package tech.tystnad.works;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用的入口文件
 * Created by wangpeng on 2017/1/24.
 */
@MapperScan("tech.tystnad.works.repository.mapper")
@SpringBootApplication
public class WorksApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorksApplication.class, args);
    }
}