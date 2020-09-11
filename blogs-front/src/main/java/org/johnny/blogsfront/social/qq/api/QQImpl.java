package org.johnny.blogsfront.social.qq.api;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.vo.social.qq.QQUserInfo;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * QQ互联接口的实现
 *
 * @author johnny
 * @create 2019-12-21 下午3:12
 **/
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private QQUserInfo existQQUserInfo;

    private String appId;

    private String appSecret;

    private String openId;

    private static final Gson gson = new Gson();
    /**
     * 获取 登录QQ用户的openId
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户的信息
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        //使用restTemplate 发送post请求 返回值位string类型
        //这里的getRestTemplate已经被重写了添加了StringHttpMessageConverter 转换
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getExistUserInfo() {
        return this.existQQUserInfo;
    }

    @Override
    public QQUserInfo getQQUserInfo() {
        QQUserInfo qqUserInfo = null;
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        if (StringUtils.isNotEmpty(result)) {
            log.info("result: " + result);
            qqUserInfo = gson.fromJson(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            this.existQQUserInfo = qqUserInfo;
        }
        return qqUserInfo;
    }

    public QQUserInfo getExistQQUserInfo() {
        return existQQUserInfo;
    }
}