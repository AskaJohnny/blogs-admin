package org.johnny.blogsserver.controller.login;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.requestvo.LoginReqVo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录 Controller
 *
 * @author johnny
 * @create 2020-07-10 下午4:14
 **/
@RestController
@Slf4j
@RequestMapping("/auth")
public class LoginController {


    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginReqVo loginReqVo) {
        log.info("【loginReqVo： {}】", loginReqVo);

        Map<String, String> resultMap = Maps.newHashMap();
        resultMap.put("token", "admin-token");
        return ResultVoUtil.success(resultMap);
    }



}