package tech.tystnad.joblog.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tech.tystnad.joblog.user.User;

@Api(tags = "Test")
@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping
	public Object index() {
		return new User();
	}
}
