package tech.tystnad.joblog.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public GenericObjectPoolConfig<?> genericObjectPoolConfig() {
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        logger.debug("Created GenericObjectPoolConfig");
        return poolConfig;
    }

    @Bean(destroyMethod = "destroy")
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettucePoolingClientConfiguration poolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .shutdownTimeout(Duration.ofSeconds(30)).poolConfig(genericObjectPoolConfig()).build();
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        standaloneConfiguration.setPassword(RedisPassword.none());
        standaloneConfiguration.setDatabase(0);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(standaloneConfiguration,
                poolingClientConfiguration);
        factory.setShareNativeConnection(false);
        logger.debug("Created LettuceConnectionFactory");
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(lettuceConnectionFactory());
        logger.debug("Created StringRedisTemplate");
        return stringRedisTemplate;
    }
}
