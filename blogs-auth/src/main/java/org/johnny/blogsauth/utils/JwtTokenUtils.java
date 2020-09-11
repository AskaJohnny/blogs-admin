package org.johnny.blogsauth.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.johnny.blogsauth.properties.JwtProperties;
import org.johnny.blogsauth.security.SecurityUser;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.*;

/**
 * JwtTokenUtils
 *
 * @author johnny
 * @create 2020-07-18 下午7:00
 **/
@Slf4j
public class JwtTokenUtils {

    public static final String SUBJECT = "blogs-admin";

    //秘钥
    public static final String APPSECRET = "onehee666";


    public static final String getJwtToken(SecurityUser securityUser, JwtProperties jwtProperties) {
        Collection<GrantedAuthority> authorities = securityUser.getAuthorities();
        Set<String> roles = new HashSet<>();
        if (CollectionUtils.isNotEmpty(authorities)) {
            for (GrantedAuthority authority : authorities) {
                String roleName = authority.getAuthority();
                roles.add(roleName);
            }
        }

        //当前时间
        long now = System.currentTimeMillis();
        //过期时间为1分钟
        long exp = now + 1000 * 60 * 60 * jwtProperties.getExpirationHour();
        //long exp = now + 10 * 60;
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(jwtProperties.getJwtSubject())
                //自定义属性 放入用户拥有请求权限
                .claim("username", securityUser.getUsername())
                .claim("userId", securityUser.getUserId())
                .claim("roles",roles)
                //失效时间
                .setExpiration(new Date(exp))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getJwtSigningKey())
                .compact();
        log.info("【jwt token str: {}】", token);
        return token;

    }

    public static final List<String> getUserRolesFromToken(String token, JwtProperties jwtProperties) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSigningKey())
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.get("roles"));
        return (List<String>) claims.get("roles");
    }
    public static final String getUserNameFromToken(String token, JwtProperties jwtProperties) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSigningKey())
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.get("username"));
        return (String) claims.get("username");
    }

    public static final Long getUserIdFromToken(String token , JwtProperties jwtProperties){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getJwtSigningKey())
                .parseClaimsJws(token)
                .getBody();
        Long userId = NumberUtils.toLong(claims.get("userId").toString());
        return userId;
    }

    public static boolean validate(String jwtToken, JwtProperties jwtProperties) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getJwtSigningKey())
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (Exception e) {
            log.error("【jwt 过期解析Exception e : {}】", e.getMessage());
            return false;
        }
        boolean flag = claims.getExpiration().after(new Date());
        return flag;
    }
}