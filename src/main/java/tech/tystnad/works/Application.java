package tech.tystnad.works;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用的入口文件
 * Created by wangpeng on 2017/1/24.
 */
@MapperScans({
        @MapperScan("tech.tystnad.works.repository.mapper"),
        @MapperScan("tech.tystnad.works.repository")})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
