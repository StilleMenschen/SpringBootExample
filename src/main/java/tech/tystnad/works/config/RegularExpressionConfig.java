package tech.tystnad.works.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegularExpressionConfig {
    @Value("${regexp.username}")
    public String userName;
    @Value("${regexp.user-password}")
    public String userPassword;
    @Value("${regexp.user-email}")
    public String userEmail;
    @Value("${regexp.user-nickname}")
    public String userNickname;
}
