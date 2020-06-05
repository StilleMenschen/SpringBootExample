package tech.tystnad.joblog.test;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.tystnad.joblog.user.User;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@RequestMapping
	public Object index() {
		redisTemplate.opsForValue().set("user:blackson", "0123456789", Duration.ofMinutes(1));
		return new User();
	}
}

