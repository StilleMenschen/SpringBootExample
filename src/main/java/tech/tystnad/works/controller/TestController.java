package tech.tystnad.works.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.tystnad.works.model.City;
import tech.tystnad.works.model.Pet;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.TestRepository;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private StringRedisTemplate redisTemplate;
    private TestRepository testRepository;

    @Autowired
    public TestController(StringRedisTemplate redisTemplate, TestRepository testRepository) {
        this.redisTemplate = redisTemplate;
        this.testRepository = testRepository;
        logger.debug("Created TestController");
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public Object index() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("user:blackson", "0123456789", Duration.ofMinutes(1));
        logger.debug("reids value is {}", operations.get("user:blackson"));
        return new User();
    }

    @GetMapping("/city/{size}")
    public List<City> findAllCity(@PathVariable("size") int size) {
        logger.warn("findAllCity");
        return testRepository.findAllCity(size);
    }

    @GetMapping("/pet/{size}")
    public List<Pet> findAllPet(@PathVariable("size") int size) {
        logger.warn("findAllPet");
        return testRepository.findAllPet(size);

    }

    @GetMapping("/pet/birth")
    @ApiOperation(value = "根据生日查询宠物", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> findPetByBirth(
            @ApiParam(type = "Date", value = "格式为 yyyy-MM-dd HH:mm:ss"
                    , example = "1900-01-01 00:00:00"
                    , required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam("birth") Date birth) {
        logger.warn("findPetByBirth");
        return testRepository.findPetByBirth(birth);
    }
}
