package tech.tystnad.works.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserTokenUtil {

    private final static String PREFIX = "user:";
    private final static int DEFAULT_TIMEOUT_SECOND = 1800;
    private StringRedisTemplate stringRedisTemplate;


}
