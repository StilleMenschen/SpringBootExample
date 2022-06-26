package com.example.demo.security;

import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtTokenVerifierFilter;
import com.example.demo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

import static com.example.demo.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserService userService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final JwtUsernameAndPasswordAuthenticationFilter jwtUsernameAndPasswordAuthenticationFilter =
                new JwtUsernameAndPasswordAuthenticationFilter(new ProviderManager(daoAuthenticationProvider()), jwtConfig, secretKey);
        jwtUsernameAndPasswordAuthenticationFilter.setFilterProcessesUrl("/api/login");

        // Cross-site Request Forgery 跨站请求伪造, 如果接口不是为浏览器提供的, 可以禁用此功能
        // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
        http.csrf().disable();
        // 启用 JWT 过滤器
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(jwtUsernameAndPasswordAuthenticationFilter)
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig, secretKey), JwtUsernameAndPasswordAuthenticationFilter.class);
        // 根据路径鉴权
        http.authorizeRequests()
                .antMatchers("/", "/api/login", "/login", "/logout", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/v1/**").hasAnyRole(STUDENT.name())
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN_TRAINEE.name(), ADMIN.name())
                .anyRequest()
                .authenticated();
//              .and().httpBasic() // 基于 HTTP 的基础验证, 需要在请求头 Authorization 中包含 BASE64 加密的用户名密码
        // 基于 FORM 表单的基础验证, 需要先使用用户名密码登录, 登录信息缓存在会话中
                /*.formLogin().loginPage("/login")
                .defaultSuccessUrl("/courses", Boolean.TRUE)
                .usernameParameter("username").passwordParameter("password")
                .and()
                // 记住用户信息 7 天内不用重复登录
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7)).key("something")
                .rememberMeParameter("remember-me")
                .and().logout().logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(Boolean.TRUE).invalidateHttpSession(Boolean.TRUE)
                .deleteCookies("JSESSIONID").logoutSuccessUrl("/login")
                .and()
                // 设置用户信息加载提供者
                .authenticationManager(new ProviderManager(daoAuthenticationProvider()))*/
        return http.build();
    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/index*");
//    }
}
