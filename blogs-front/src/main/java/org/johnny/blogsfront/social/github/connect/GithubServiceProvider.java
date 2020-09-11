package org.johnny.blogsfront.social.github.connect;

import org.johnny.blogsfront.social.github.api.Github;
import org.johnny.blogsfront.social.github.api.GithubImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * QQ的ServiceProvider
 *
 * @author johnny
 * @create 2019-12-21 下午12:47
 **/
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<Github> {


    private String client_id;

    /**
     *  获取 authorizeCode 获取授权码的 路径
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     *  获取 accessToken的 路径
     */
    private static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";

    /**
     *
     */
    public GithubServiceProvider(String client_id , String appSecret) {

        super(new GithubOAuth2Template(client_id,appSecret,URL_AUTHORIZE , URL_ACCESS_TOKEN));
        this.client_id = client_id;
    }


    @Override
    public Github getApi(String accessToken) {
        return new GithubImpl(accessToken , client_id);
    }
}