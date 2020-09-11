package org.johnny.blogsfront.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author johnny
 * @create 2019-12-23 下午2:39
 **/
//@RestController
public class ToHomeController {


   // @GetMapping("/")
    @CrossOrigin
    public void toHome(HttpServletResponse response) throws IOException {

//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authenticationToken = securityContext.getAuthentication();
//        System.out.println(authenticationToken);
//        if(authenticationToken != null){
//            String id = ((SocialUser)authenticationToken.getPrincipal()).getUsername();
//            System.out.println("认证成功 ID:" + id);
//            response.setHeader("JWT-TOKEN" ,"abc");
//        }
        //response.setHeader("JWT-TOKEN" ,"abc");
      //  response.setHeader("Access-Control-Expose-Headers","Authorization");
        //TODO 后期可能要改成 重定向到 对应路径 这个只能是 http://www.askajohnny.com/#/leaveword 暂时不知道为什么
        response.sendRedirect("http://www.askajohnny.com/#/leaveword");
    }


}