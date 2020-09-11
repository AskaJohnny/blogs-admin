package org.johnny.blogsauth.handler;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.johnny.blogsauth.properties.JwtProperties;
import org.johnny.blogsauth.security.SecurityUser;
import org.johnny.blogsauth.utils.JwtTokenUtils;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.JwtPayloadBuilder;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author johnny
 * @create 2020-07-18 下午2:42
 **/
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        String token = JwtTokenUtils.getJwtToken(securityUser, jwtProperties);
        log.info("【jwt token】：{}", token);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", token);
        ResultVo resultVo = ResultVoUtil.success(resultMap);
        String result = GsonUtils.toJsonStr(resultVo);
        response.getWriter().write(result);
    }


}