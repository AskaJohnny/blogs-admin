package org.johnny.blogsauth.voter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义的 投票器
 *
 * @author johnny
 * @create 2020-07-19 上午12:41
 **/
public class RoleBasedVoter implements AccessDecisionVoter<Object> {


    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (ConfigAttribute attribute : attributes) {
            if (attribute.getAttribute() == null) {
                continue;
            }
            //默认的SpringSecurity的投票器，比如RoleVoter 的 support方法会去判断角色是否 包含ROLE_前缀
            //我们这里不做这种限制
            if (this.supports(attribute)) {
                for (GrantedAuthority authority : authorities) {
                    if(attribute.getAttribute().equals(authority.getAuthority())){
                        return ACCESS_GRANTED;
                    }
                }
            }
        }
        return ACCESS_DENIED;
    }
}