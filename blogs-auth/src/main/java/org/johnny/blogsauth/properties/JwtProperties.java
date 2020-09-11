package org.johnny.blogsauth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT的可配置 参数
 *
 * @author johnny
 * @create 2020-07-18 下午11:48
 **/
@Component
@ConfigurationProperties(prefix = "system.jwt")
@Data
public class JwtProperties {


    private String jwtSubject = "blogs-admin";

    private String jwtSigningKey = "blogs-admin";

    private long expirationHour = 1;
}