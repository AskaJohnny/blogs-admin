package org.johnny.blogsfront.social.qq.connect;

import org.johnny.blogsfront.social.qq.api.QQ;
import org.johnny.blogsfront.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * QQ的ServiceProvider
 *
 * @author johnny
 * @create 2019-12-21 下午12:47
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appId;

    /**
     *  获取 authorizeCode 获取授权码的 路径
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     *  获取 accessToken的 路径
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     *
     */
    public QQServiceProvider(String appId , String appSecret) {

        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE , URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken , appId);
    }
}