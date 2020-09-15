package tech.tystnad.works.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * Created by wangpeng on 2017/4/19.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /*
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 使用过滤器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许跨域的Cookie
        config.addAllowedHeader(CorsConfiguration.ALL); // 允许所有请求头
        config.addAllowedMethod(HttpMethod.HEAD); // 允许所有请求方法
        config.addAllowedMethod(HttpMethod.POST); // 允许所有请求方法
        config.addAllowedMethod(HttpMethod.GET); // 允许所有请求方法
        config.addAllowedMethod(HttpMethod.PUT); // 允许所有请求方法
        config.addAllowedMethod(HttpMethod.DELETE); // 允许所有请求方法
        config.addAllowedMethod(HttpMethod.OPTIONS); // 允许所有请求方法
        config.setMaxAge(Duration.ofHours(24)); // 跨域预检请求缓存最大时长为24小时
        source.registerCorsConfiguration("/**", config); // 过滤所有访问路径
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    */

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // 使用配置注册
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowCredentials(true) // 允许跨域的Cookie
                        .allowedHeaders("*") // 允许所有请求头
                        .allowedOrigins("http://localhost", "http://127.0.0.1", "http://localhost:8080",
                                "http://localhost:3000", "chrome-extension://aejoelaoggembcahagimdiliamlcdmfm")
                        .allowedMethods("HEAD", "POST", "GET", "PUT", "DELETE", "OPTIONS")
                        .maxAge(Duration.ofDays(1).getSeconds());
            }
        };
    }
}
