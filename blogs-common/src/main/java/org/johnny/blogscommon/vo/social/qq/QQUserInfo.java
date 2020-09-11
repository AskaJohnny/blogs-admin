package org.johnny.blogscommon.vo.social.qq;

import lombok.Data;

/**
 * QQ返回的用户信息
 * @author johnny
 * @create 2019-12-21 下午12:54
 **/
@Data
public class QQUserInfo {

    private int ret;
    private String msg;
    private String is_lost;
    private String nickname;
    private String gender;
    private String province;

    private String city;

    private String year;

    private String constellation;


    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq;

    private String figureurl_type;

    private String figureurl_qq_1;
    private String figureurl_qq_2;

    private String is_yellow_vip;
    private String vip;
    private String yellow_vip_level;
    private String level;
    private String is_yellow_year_vip;

    private String openId;

}