package org.johnny.blogsauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author johnny
 * @create 2020-07-20 上午9:15
 **/
@Component
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("【LoginFailHandler ......】");
        ResultVo resultVo = ResultVoUtil.loginFail();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(GsonUtils.toJsonStr(resultVo));

    }
}