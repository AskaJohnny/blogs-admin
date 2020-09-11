package org.johnny.blogsfront.social;

import org.johnny.blogsfront.security.handler.SocialLoginFailHandler;
import org.johnny.blogsfront.security.handler.SocialLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author johnny
 * @create 2019-12-21 下午1:20
 **/
@Component
public class CustomSpringSocialConfigurer extends SpringSocialConfigurer {


    @Autowired
    private SocialLoginSuccessHandler socialLoginSuccessHandler;

    @Autowired
    private SocialLoginFailHandler socialLoginFailHandler;

    /**
     * 主要是为 SocialAuthenticationFilter 添加一个 拦截请求的url 默认是 -》 private static final String DEFAULT_FILTER_PROCESSES_URL = "/auth";
     * @param object
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        socialAuthenticationFilter.setAuthenticationFailureHandler(socialLoginFailHandler);
        socialAuthenticationFilter.setAuthenticationSuccessHandler(socialLoginSuccessHandler);
        //可以配置 拦截 的请求
        //socialAuthenticationFilter.setFilterProcessesUrl("/qqLogin");
        return super.postProcess(object);
    }
}