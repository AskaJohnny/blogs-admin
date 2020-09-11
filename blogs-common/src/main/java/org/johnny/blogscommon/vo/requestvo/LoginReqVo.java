package org.johnny.blogscommon.vo.requestvo;

import lombok.Data;

/**
 * 登录请求的 Vo
 *
 * @author johnny
 * @create 2020-07-10 下午4:20
 **/
@Data
public class LoginReqVo {

    private String username;

    private String password;
}