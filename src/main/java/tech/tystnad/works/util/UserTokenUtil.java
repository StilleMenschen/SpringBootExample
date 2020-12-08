package tech.tystnad.works.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import tech.tystnad.works.model.SysUser;
import tech.tystnad.works.properties.JwtProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Component
public class UserTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenUtil.class);

    private JwtProperties jwtProperties;
    private final String PREFIX = "user:";
    private StringRedisTemplate stringRedisTemplate;

    public UserTokenUtil() {
        super();
    }

    @Autowired
    public UserTokenUtil(JwtProperties jwtProperties, StringRedisTemplate stringRedisTemplate) {
        this.jwtProperties = jwtProperties;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void saveUser(SysUser user) {
        final String key = PREFIX.concat(user.getUserName());
        HashOperations<String, String, String> operations = stringRedisTemplate.opsForHash();
        operations.put(key, UserField.NICKNAME, user.getNickname());
        operations.put(key, UserField.EMAIL, user.getEmail());
        operations.put(key, UserField.ORG_ID, String.valueOf(user.getOrgId()));
        operations.put(key, UserField.TOP_ID, String.valueOf(user.getTopId()));
        operations.put(key, UserField.ROLE_ID, String.valueOf(user.getRoleId()));
        operations.put(key, UserField.UPDATE_TIME, Long.toString(user.getUpdateTime().getTime()));
        operations.put(key, UserField.LAST_PASSWORD_RESET_DATE, Long.toString(user.getLastPasswordResetTime().getTime()));
        stringRedisTemplate.expire(key, Duration.ofSeconds(jwtProperties.getExpiration()));
    }

    public boolean refreshUser(SysUser user) {
        final String key = PREFIX.concat(user.getUserName());
        if (hasUser(key)) {
            stringRedisTemplate.expire(key, Duration.ofSeconds(jwtProperties.getExpiration()));
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public SysUser getUser(String userName) {
        final String key = PREFIX.concat(userName);
        if (hasUser(key)) {
            String temp;
            SysUser user = new SysUser();
            HashOperations<String, String, String> operations = stringRedisTemplate.opsForHash();
            user.setNickname(operations.get(key, UserField.NICKNAME));
            user.setEmail(operations.get(key, UserField.EMAIL));
            user.setOrgId(Long.valueOf(operations.get(key, UserField.ORG_ID)));
            user.setTopId(Long.valueOf(operations.get(key, UserField.TOP_ID)));
            user.setRoleId(Long.valueOf(operations.get(key, UserField.ROLE_ID)));
            temp = operations.get(key, UserField.UPDATE_TIME);
            user.setUpdateTime(new Date(Long.valueOf(temp)));
            temp = operations.get(key, UserField.LAST_PASSWORD_RESET_DATE);
            user.setLastPasswordResetTime(new Date(Long.valueOf(temp)));
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
        public final static String ORG_ID = "orgId";
        public final static String TOP_ID = "topId";
        public final static String ROLE_ID = "roleId";
        public final static String UPDATE_TIME = "updateTime";
        public final static String LAST_PASSWORD_RESET_DATE = "lastPasswordResetDate";
    }
}
