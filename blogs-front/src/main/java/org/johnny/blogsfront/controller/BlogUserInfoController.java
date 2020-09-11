package org.johnny.blogsfront.controller;


import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.johnny.blogscommon.repository.blog.BlogUserInfoRepository;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author johnny
 * @create 2019-12-23 下午5:06
 **/
@RestController
@RequestMapping("/blogUser")
@Slf4j
public class BlogUserInfoController {

    @Autowired
    private BlogUserInfoRepository blogUserInfoRepository;


    @RequestMapping("/getUserByToken")
    public ResultVo getUserbyToken(@RequestParam("jwt-token") String jwtToken ){
        log.info("【getUserByToken jwtToken：{}】"  , jwtToken);
        jwtToken = "14";
        BlogUserInfo blogUserInfo = blogUserInfoRepository.findById(Long.valueOf(jwtToken)).orElse(null);
        Map<String,Object> map = new HashMap<>();
        map.put("authUser" , blogUserInfo);
        return ResultVo.success(map);
    }

    @GetMapping("/checkLogin")
    public ResultVo checkLogin(){
        log.info("【checkLogin 】");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("【checkLogin authentication {} 】" ,authentication);
        if(authentication  != null && authentication instanceof SocialAuthenticationToken){
            log.info("【checkLogin authentication success 】");
            return ResultVoUtil.success();
        }else{
            log.info("【checkLogin authentication fail 】");
            return ResultVoUtil.error(403,"NO AUTHENTICATION");
        }
    }
}