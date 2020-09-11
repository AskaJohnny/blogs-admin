package org.johnny.blogsfront.security.handler;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.repository.blog.BlogUserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author johnny
 * @create 2020-07-18 下午2:42
 **/
@Component
@Slf4j
@Builder
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private BlogUserInfoRepository blogUserInfoRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //CustomSocialUser socialUser = (CustomSocialUser) authentication.getPrincipal();
        //String id = socialUser.getUserId();
        //BlogUserInfo blogUserInfo = blogUserInfoRepository.findById(Long.valueOf(id)).orElse(null);

        //log.info("auth blogUserInfo： {}" , blogUserInfo);
        //封装成JWT TOKEN 给前端
        //Socket 去发送
        //webSocketServer.sendMessage("Hello");
        //response.setHeader("JWT-TOKEN" , id);
        //response.addHeader("userInfo", URLEncoder.encode(blogUserInfo.getUserName() , "utf-8"));
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        //重定向到 这个
        response.sendRedirect("http://www.askajohnny.com/#/leaveword");
        //response.getWriter().write("hello");
    }


}