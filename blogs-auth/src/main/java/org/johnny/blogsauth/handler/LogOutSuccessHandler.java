package org.johnny.blogsauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录
 *
 * @author johnny
 * @create 2020-07-19 下午3:28
 **/
@Component
@Slf4j
public class LogOutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("【退出登录成功onLogoutSuccess】");
        ResultVo resultVo = ResultVoUtil.logOut();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(GsonUtils.toJsonStr(resultVo));
    }
}