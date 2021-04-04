package tech.tystnad.works;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Boot 应用的入口文件
 * Created by wangpeng on 2017/1/24.
 */
@MapperScan("tech.tystnad.works.repository.mapper")
@SpringBootApplication
@Controller
public class WorksApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WorksApplication.class);
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(WorksApplication.class, args);
    }

    @RequestMapping(value = "/test/close", method = RequestMethod.HEAD)
    public ResponseEntity<Integer> close(HttpServletRequest request) {
        logger.debug(request.getRequestURI());
        final String URL = request.getRequestURL().toString();
        logger.debug(URL);
        if (URL.startsWith("http://localhost")) {
            logger.warn("Application will be close!");
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("close...");
                    context.close();
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        return ResponseEntity.ok(0);
    }

    @Override
    public void run(String... args) {
        logger.debug("http://localhost:8080/swagger-ui/");
    }
}
