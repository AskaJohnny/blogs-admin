package org.johnny.blogsserver.controller.user;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogsauth.properties.JwtProperties;
import org.johnny.blogsauth.security.SecurityUser;
import org.johnny.blogsauth.utils.JwtTokenUtils;
import org.johnny.blogsauth.utils.SecurityUtils;
import org.johnny.blogscommon.service.system.UserService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.requestvo.LoginReqVo;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户 Controller
 *
 * @author johnny
 * @create 2020-07-10 下午4:08
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/logout")
    public ResultVo userInfo() {



        return ResultVoUtil.success();
    }

    @GetMapping("/userInfo")
    public ResultVo userInfo(@RequestParam("token") String token) {
        Long userId = SecurityUtils.getSecurityUserId();
        log.info("【userId： {}】", userId);
        UserResultVo userResultVo = userService.findUserInfo(userId);
        userResultVo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userResultVo.setIntroduction("我是超级管理员");
        userResultVo.setName(SecurityUtils.getSecurityUserName());

        return ResultVoUtil.success(userResultVo);
    }

    @GetMapping("/listByCondition")
    public ResultVo listByCondition(UserReqVo userReqVo, PageVo pageVo) {
        pageVo.setPageNumber(pageVo.getPageNumber() - 1);
        Page<UserResultVo> page = userService.listByCondition(userReqVo, PageUtil.initPage(pageVo));
        return ResultVoUtil.success(page);
    }


    @PostMapping("/submitUser")
    public ResultVo submitUser(@RequestBody UserReqVo userReqVo) {
        log.info("【userReqVo ： {}】  ", userReqVo);

        //
        userService.submitUser(userReqVo);

        return ResultVoUtil.success();
    }
}