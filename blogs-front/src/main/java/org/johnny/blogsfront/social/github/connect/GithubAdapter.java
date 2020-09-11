package org.johnny.blogsfront.social.github.connect;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.vo.social.github.GithubUserInfo;
import org.johnny.blogsfront.social.github.api.Github;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Github 的apdater 主要用于将github返回的用户信息 适配到通用的 Connection 连接里
 *
 * @author johnny
 * @create 2019-12-25 下午11:52
 **/
@Slf4j
public class GithubAdapter implements ApiAdapter<Github> {


    @Override
    public boolean test(Github api) {
        //测试 github服务是否可用
        return true;
    }

    @Override
    public void setConnectionValues(Github api, ConnectionValues values) {

        //调用github  api接口 获取github的用户信息
        GithubUserInfo githubUserInfo = api.getUserInfo();

        //将用户信息的一些特定的属性 专递给ConnectionValues 供 Connection 使用
        values.setDisplayName(githubUserInfo.getLogin());
        values.setImageUrl(githubUserInfo.getAvatar_url());
        values.setProfileUrl(githubUserInfo.getHtml_url());
        values.setProviderUserId(String.valueOf(githubUserInfo.getId()));
    }



    @Override
    public UserProfile fetchUserProfile(Github api) {
        return null;
    }

    @Override
    public void updateStatus(Github api, String message) {

    }
}