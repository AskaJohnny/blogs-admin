package org.johnny.blogsfront.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author johnny
 * @create 2020-08-07 下午2:02
 **/
@Component
@ConfigurationProperties(prefix = "system.jwt")
@Data
public class JwtProperties {

    private String jwtSubject = "blogs";

    private String jwtSigningKey = "blogs";

    private long expirationHour = 24 * 7;
}