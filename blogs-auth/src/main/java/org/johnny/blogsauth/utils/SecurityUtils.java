package org.johnny.blogsauth.utils;

import org.johnny.blogsauth.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author johnny
 * @create 2020-07-19 下午2:01
 **/
public class SecurityUtils {

    public static final String getSecurityUserName() {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return securityUser.getUsername();
    }

    public static final Long getSecurityUserId() {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return securityUser.getUserId();
    }

    public static final Set<String> getSecurityUserGrantedAuthority() {
        Set<String> roles = new HashSet<>();
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        grantedAuthorities.forEach(grantedAuthority -> {
            roles.add(grantedAuthority.getAuthority());
        });
        return roles;
    }

}