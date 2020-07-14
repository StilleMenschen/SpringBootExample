package tech.tystnad.works.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import tech.tystnad.works.model.SysUser;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Component
public class UserTokenUtil {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.expiration}")
    private long DEFAULT_TIMEOUT_SECONDS;
    private final String PREFIX = "user:";
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private StringRedisTemplate stringRedisTemplate;

    public UserTokenUtil() {
        super();
    }

    @Autowired
    public UserTokenUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void saveUser(SysUser user) {
        final String key = PREFIX.concat(user.getUser_name());
        HashOperations<String, String, String> operations = stringRedisTemplate.opsForHash();
        operations.put(key, UserField.NICKNAME, user.getNickname());
        operations.put(key, UserField.EMAIL, user.getEmail());
        operations.put(key, UserField.ORG_ID, String.valueOf(user.getOrg_id()));
        operations.put(key, UserField.TOP_ID, String.valueOf(user.getTop_id()));
        operations.put(key, UserField.ROLE_ID, String.valueOf(user.getRole_id()));
        operations.put(key, UserField.UPDATE_TIME, sdf.format(user.getUpdate_time()));
        stringRedisTemplate.expire(key, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    public boolean refreshUser(SysUser user) {
        final String key = PREFIX.concat(user.getUser_name());
        if (hasUser(key)) {
            stringRedisTemplate.expire(key, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            return true;
        }
        return false;
    }

    public SysUser getUser(String userName) {
        final String key = PREFIX.concat(userName);
        if (hasUser(key)) {
            HashOperations<String, String, String> operations = stringRedisTemplate.opsForHash();
            SysUser user = new SysUser();
            user.setNickname(operations.get(key, UserField.NICKNAME));
            user.setEmail(operations.get(key, UserField.EMAIL));
            user.setOrg_id(Long.valueOf(operations.get(key, UserField.ORG_ID)));
            user.setTop_id(Long.valueOf(operations.get(key, UserField.TOP_ID)));
            user.setRole_id(Long.valueOf(operations.get(key, UserField.ROLE_ID)));
            try {
                Date temp = sdf.parse(operations.get(key, UserField.UPDATE_TIME));
                user.setUpdate_time(new Timestamp(temp.getTime()));
            } catch (ParseException e) {
                logger.warn(e.getMessage());
            }
            return user;
        }
        return null;
    }

    public boolean removeUser(String userName) {
        return stringRedisTemplate.delete(userName);
    }

    public boolean hasUser(String userName) {
        return stringRedisTemplate.hasKey(userName);
    }

    private class UserField {
        public final static String NICKNAME = "nickname";
        public final static String EMAIL = "email";
        public final static String ORG_ID = "org_id";
        public final static String TOP_ID = "top_id";
        public final static String ROLE_ID = "role_id";
        public final static String UPDATE_TIME = "update_time";
    }
}
