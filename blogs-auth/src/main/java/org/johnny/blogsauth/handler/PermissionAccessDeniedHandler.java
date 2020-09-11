package org.johnny.blogsauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问 请求 被拒绝的 处理器
 *
 * @author johnny
 * @create 2020-07-21 下午1:07
 **/
@Slf4j
@Component
public class PermissionAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("【PermissionAccessDeniedHandler ......】");
        ResultVo resultVo = ResultVoUtil.accessReject();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(GsonUtils.toJsonStr(resultVo));
    }
}