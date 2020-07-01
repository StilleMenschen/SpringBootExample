package tech.tystnad.works.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redis_host;

    @Value("${spring.redis.port}")
    private int redis_port;

    @Value("${spring.redis.database}")
    private int redis_database;

    @Bean
    public GenericObjectPoolConfig<?> genericObjectPoolConfig() {
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        return poolConfig;
    }

    @Bean(destroyMethod = "destroy")
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettucePoolingClientConfiguration poolingClientConfiguration = LettucePoolingClientConfiguration.builder()
                .shutdownTimeout(Duration.ofSeconds(30)).poolConfig(genericObjectPoolConfig()).build();
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(redis_host, redis_port);
        standaloneConfiguration.setPassword(RedisPassword.none());
        standaloneConfiguration.setDatabase(redis_database);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(standaloneConfiguration,
                poolingClientConfiguration);
        factory.setShareNativeConnection(false);
        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(lettuceConnectionFactory());
    }
}
