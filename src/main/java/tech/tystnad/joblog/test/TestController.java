package tech.tystnad.joblog.test;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tech.tystnad.joblog.user.User;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private StringRedisTemplate redisTemplate;
    private TestRepository repository;

    @Autowired
    public TestController(StringRedisTemplate redisTemplate, TestRepository repository) {
        this.redisTemplate = redisTemplate;
        this.repository = repository;
        logger.debug("Created TestController");
    }

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public Object index() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("user:blackson", "0123456789", Duration.ofMinutes(1));
        logger.debug("reids value is {}", operations.get("user:blackson"));
        return new User();
    }

    @GetMapping("/city/{size}")
    public List<City> findAllCity(@PathVariable("size") int size) {
        logger.warn("findAllCity");
        return repository.findAll(size);
    }
}
