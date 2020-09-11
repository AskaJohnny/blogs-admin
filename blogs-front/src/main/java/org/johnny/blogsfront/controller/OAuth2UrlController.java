package org.johnny.blogsfront.controller;

import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author johnny
 * @create 2019-12-25 下午10:40
 **/

@RestController
@RequestMapping("/oauth2")
public class OAuth2UrlController {


    public static String STATE;

    @GetMapping("/getGithubAuthUrl")
    @CrossOrigin
    public ResultVo getAuthUrl() {

        Map<String, String> map = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        STATE = uuid;
        String url = "https://github.com/login/oauth/authorize?client_id=b28eb51f9f9cb8592e8b&state=" + STATE + "&redirect_uri=http://www.askajohnny.com/blogs/auth/github";
        map.put("result", url);
        //String authUrl  =MySocialFailHandler.authUrl;
        return ResultVoUtil.success(map);
    }
}