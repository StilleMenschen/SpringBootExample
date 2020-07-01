package tech.tystnad.works.test;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import tech.tystnad.works.model.City;
import tech.tystnad.works.repository.TestRepository;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AuthApplicationTests {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    void contextLoads() {
        TestRepository repository = sqlSessionTemplate.getMapper(TestRepository.class);
        List<City> cityList = repository.findByDistrict("A");
        cityList.forEach(System.out::println);
    }

}
