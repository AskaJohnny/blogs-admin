package org.johnny.blogsfront.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @author johnny
 * @create 2019-12-21 下午12:48
 **/
@Slf4j
public class QQOAuth2Template extends OAuth2Template {


    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //设置带上 clientId 和 clientSecret 默认它是false
        this.setUseParametersForClientAuthentication(true);
    }

    /**
     * 这里在父类创建的RestTemplate里添加StringHttpMessageConverter 供转换QQ使用
     * @return
     */
    @Override
    protected RestTemplate createRestTemplate() {
         RestTemplate restTemplate = super.createRestTemplate();
         restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
         return restTemplate;
    }


    /**
     * 这个接口必须重写
     * 原因：在获取QQ的accessToken的时候 它返回的是 String
     * 而 覆盖这个 getRestTemplate().postForObject(accessTokenUrl, parameters, Map.class) 因为解析不了Map类型
     * @param accessTokenUrl
     * @param parameters
     * @return
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {

        //这里是重点  将其返回类型指定为String
        String responseToken = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取到accessToken :" + responseToken );

        // access_token=FE04************************CCE2 & expires_in=7776000 & refresh_token=88E4************************BE14

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseToken,"&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1],"="));
        String refresh_token = StringUtils.substringBeforeLast(items[2],"=");

        return new AccessGrant(accessToken , null , refresh_token , expiresIn);
    }
}