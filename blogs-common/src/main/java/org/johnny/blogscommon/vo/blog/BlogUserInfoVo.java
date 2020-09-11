package org.johnny.blogscommon.vo.blog;

import lombok.Data;

/**
 * @author johnny
 * @create 2019-12-19 下午11:10
 **/
@Data
public class BlogUserInfoVo {

    private Long id;

    /**
     * 第三方平台 或者 自己注册的账号
     */
    private String userName;

    /**
     * 头像
     */
    private String headImage;

}