package org.johnny.blogsfront.social.github.api;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.vo.social.github.GithubUserInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author johnny
 * @create 2019-12-25 下午10:28
 **/
@Slf4j
public class GithubImpl extends AbstractOAuth2ApiBinding implements Github {

    private GithubUserInfo githubUserInfo;

    private String client_id;

    private String client_secret;

    private String accessToken;

    private static final String URL_GET_USERINFO = "https://api.github.com/user?access_token=%s";


    public GithubImpl(String accessToken, String client_id) {

        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
        this.client_id = client_id;
    }

    @Override
    public GithubUserInfo getUserInfo() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token "+accessToken);
        headers.add("User-Agent" , "Johnny小屋");

        String url = String.format(URL_GET_USERINFO, accessToken);
        //使用restTemplate 发送post请求 返回值位string类型
        //这里的getRestTemplate已经被重写了添加了StringHttpMessageConverter 转换
        String result = getRestTemplate().getForObject(url, String.class, headers);
        Gson gson = new Gson();
        GithubUserInfo githubUserInfo = gson.fromJson(result, GithubUserInfo.class);
        log.info("【github UserInfo： {}】 ", githubUserInfo);
        this.githubUserInfo = githubUserInfo;
        return githubUserInfo;
    }

    @Override
    public GithubUserInfo getExistUserInfo() {
        return this.githubUserInfo;
    }
}