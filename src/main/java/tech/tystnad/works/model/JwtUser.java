package tech.tystnad.works.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 9089079004248710985L;
    private final Long userId;
    private final Long topId;
    private final Long orgId;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;

    public JwtUser(Long userId, Long topId, Long orgId, String username, String password, boolean enabled, String email,
                   Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate) {
        this.userId = userId;
        this.topId = topId;
        this.orgId = orgId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    @JsonIgnore
    public Long getTopId() {
        return topId;
    }

    @JsonIgnore
    public Long getOrgId() {
        return orgId;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
