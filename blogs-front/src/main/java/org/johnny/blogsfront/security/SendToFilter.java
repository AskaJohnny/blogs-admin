package org.johnny.blogsfront.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author johnny
 * @create 2019-12-23 上午12:22
 **/
public class SendToFilter  extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        HttpServletResponse reps = (HttpServletResponse) response;
        //reps.setHeader("Access-Control-Allow-Origin" , "*");
        reps.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        reps.setHeader("Access-Control-Max-Age", "3600");
        reps.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
       // reps.sendRedirect("http://graph.qq.com/oauth2.0/show?which=Login&display=pc&client_id=101583210&response_type=code&redirect_uri=http%3A%2F%2Fwww.askajohnny.com%2Fblogs%2Fauth%2Fqq&state=0bb793c8-59de-437f-979f-668137ce5f43");
//        reps.sendRedirect("https://www.baidu.com");
        filterChain.doFilter(request,response);
        return;

    }
}