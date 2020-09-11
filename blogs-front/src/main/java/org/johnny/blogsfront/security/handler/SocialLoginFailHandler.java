package org.johnny.blogsfront.security.handler;

import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author johnny
 * @create 2019-12-22 下午11:27
 **/
@Component
public class SocialLoginFailHandler implements AuthenticationFailureHandler {

    public static  String authUrl = "";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        //目前只是用于 返回 跳转到 QQ登录的 authUrl 给前端 前端收到后进行跳转
        if(failed instanceof  SocialAuthenticationRedirectException){
            authUrl = ((SocialAuthenticationRedirectException) failed).getRedirectUrl();
            Map<String,String> map = new HashMap<>();
            map.put("authUrl",authUrl);
            ResultVo resultVo = ResultVoUtil.success(map);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(GsonUtils.toJsonStr(resultVo));
        }
    }
}