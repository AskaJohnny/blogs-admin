package org.johnny.blogsauth.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  登录过期 走这个 JwtAuthenticationEntryPoint
 *  登录失败（如 账号错误 走 LoginFailHandler）
 *  权限不足 走 PermissionAccessDeniedHandler
 *  退出登录 成功走 LogOutSuccessHandler
 * @author johnny
 * @create 2020-07-18 下午1:40
 **/
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("【JwtAuthenticationEntryPoint ");
        ResultVo resultVo = ResultVoUtil.expire();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(GsonUtils.toJsonStr(resultVo));
    }
}