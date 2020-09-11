package org.johnny.blogsfront.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.utils.GsonUtils;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogsfront.security.CustomSocialUser;
import org.johnny.blogsfront.security.jwt.JwtProperties;
import org.johnny.blogsfront.security.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT token filter
 *
 * @author johnny
 * @create 2020-08-07 下午1:26
 **/
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        //1.校验是否已经存在 token
        String requestUrI = request.getRequestURI();
        log.info("--------------请求地址: " + requestUrI);
        if (requestUrI.matches("auth/qq/*")) {
            //保存 当前的
            log.info("【】");
        }


        final String jwtToken = request.getHeader("JWT-TOKEN");
        if (StringUtils.isNotEmpty(jwtToken)) {
            String username = null;
            try {
                if (JwtTokenUtils.validate(jwtToken, jwtProperties)) {
                    username = JwtTokenUtils.getUserNameFromToken(jwtToken, jwtProperties);
                    chain.doFilter(request, response);
                } else {
                    log.info("【过期 ； {}】", username);
                    ResultVo resultVo = ResultVoUtil.expire();
                    String result = GsonUtils.toJsonStr(resultVo);
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                    SecurityContextHolder.getContext().setAuthentication(null);
                    response.getWriter().write(result);
                }
            } catch (ExpiredJwtException e) {
                log.info("【过期 ； {}】", username);
                ResultVo resultVo = ResultVoUtil.expire();
                String result = GsonUtils.toJsonStr(resultVo);
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                new SecurityContextLogoutHandler().logout(request, response, auth);
                SecurityContextHolder.getContext().setAuthentication(null);
                response.getWriter().write(result);
            }
        } else {
            //2.不存在 判断当前是否已经登录   有可能是刚刚登录，然后重定向到 留言页面，然后还未分发token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof SocialAuthenticationToken) {
                //表示已经登录过
                //SocialUser socialUser = (SocialUser)authentication.getPrincipal();
                CustomSocialUser socialUser = (CustomSocialUser) authentication.getPrincipal();
                String id = socialUser.getUserId();
                //BlogUserInfo blogUserInfo = blogUserInfoRepository.findById(Long.valueOf(id)).orElse(null);
                log.info("【用户已登录 : Id {} 】", id);
                //
                String token = JwtTokenUtils.getJwtToken(socialUser, jwtProperties);
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("token", token);
                response.setHeader("jwt-token", token);
                response.setHeader("Access-Control-Expose-Headers", "Authorization");
//                ResultVO resultVo = ResultVOUtil.success(resultMap);
//                String result = GsonUtils.toJson(resultVo);
//                response.getWriter().write(result);
            }

            chain.doFilter(request, response);
        }

    }
}