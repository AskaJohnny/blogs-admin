package org.johnny.blogsauth.filter;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogsauth.properties.JwtProperties;
import org.johnny.blogsauth.security.SecurityUser;
import org.johnny.blogsauth.utils.JwtTokenUtils;
import org.johnny.blogsauth.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义JwtTokenFilter
 *
 * @author johnny
 * @create 2020-07-18 下午8:28
 **/
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String jwtToken = request.getHeader("JWT-TOKEN");
        String username = null;
        if (StringUtils.isNotEmpty(jwtToken)) {
            try {
                if(JwtTokenUtils.validate(jwtToken,jwtProperties)){
                    username = JwtTokenUtils.getUserNameFromToken(jwtToken,jwtProperties);
                }else{
                    log.info("【过期 ； {}】", username);
                }
                log.info("【payload ； {}】", username);
            } catch (ExpiredJwtException e) {
            }
        }
        if(StringUtils.isNotEmpty(username)){
            List<String> roles = JwtTokenUtils.getUserRolesFromToken(jwtToken,jwtProperties);
            Long userId = JwtTokenUtils.getUserIdFromToken(jwtToken,jwtProperties);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            roles.forEach(role ->{
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(grantedAuthority);
            });

            SecurityUser securityUser = new SecurityUser(userId,username,null,grantedAuthorities);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}