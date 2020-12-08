package tech.tystnad.works.factory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.SysUser;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUser user) {
        return new JwtUser(user.getUserId(), user.getUserName(), user.getUserCipher(), user.isEnabled(), user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()), user.getLastPasswordResetTime());
    }

    /**
     * 简单地将字符串转为权限
     * @param authorities
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
