package org.johnny.blogsfront.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

/**
 * 自定义的 SocialUser
 *
 * @author johnny
 * @create 2020-08-07 下午1:43
 **/
@Data
@Builder
public class CustomSocialUser implements SocialUserDetails {


    private String  username;

    private String password;

    private String id;

    private String headImage;

    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public String getUserId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    @Override
    public String getPassword() {
        return null;
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