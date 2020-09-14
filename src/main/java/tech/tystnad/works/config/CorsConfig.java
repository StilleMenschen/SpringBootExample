package tech.tystnad.works.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Duration;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许跨域的Cookie
        config.addAllowedOrigin("http://localhost"); // 允许跨域访问本服务的其它域
        config.addAllowedOrigin("http://127.0.0.1");
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*"); // 允许所有请求头
        config.addAllowedMethod("HEAD"); // 允许所有请求方法
        config.addAllowedMethod("POST"); // 允许所有请求方法
        config.addAllowedMethod("GET"); // 允许所有请求方法
        config.addAllowedMethod("PUT"); // 允许所有请求方法
        config.addAllowedMethod("DELETE"); // 允许所有请求方法
        config.addAllowedMethod("OPTIONS"); // 允许所有请求方法
        config.setMaxAge(Duration.ofHours(24)); // 跨域预检请求缓存最大时长为24小时
        source.registerCorsConfiguration("/**", config); // 过滤所有访问路径
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
