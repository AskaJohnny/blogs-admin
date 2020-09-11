package org.johnny.blogscommon.entity.blog;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 登录的用户信息
 *
 * @author johnny
 * @create 2019-12-19 下午4:47
 **/
@Data
@Entity
public class BlogUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 第三方平台 或者 自己注册的账号
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 头像
     */
    private String headImage;


    /**
     * 性别
     */
    private String gender;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * year
     */
    private Integer year;

    private String profileUrl;

    private String providerId;

    private String role;
}