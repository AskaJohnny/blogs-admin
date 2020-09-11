package org.johnny.blogsauth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 安全用户
 *
 * @author johnny
 * @create 2020-07-19 下午1:41
 **/
public class SecurityUser implements UserDetails {

    private Collection<GrantedAuthority> authorities;

    private String username;

    private Long userId;

    private String password;

    public SecurityUser(Long userId,String username, String encode, Collection<GrantedAuthority> grantedAuthorities) {
        this.userId = userId;
        this.username = username;
        this.password = encode;
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}