package org.johnny.blogsfront.social.github.connect;

import org.johnny.blogsfront.social.github.api.Github;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * QQ的ConnectionFactory
 *
 * @author johnny
 * @create 2019-12-21 下午12:30
 **/
public class GithubConnectionFactory extends OAuth2ConnectionFactory<Github> {


    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     */
    public GithubConnectionFactory(String providerId, String appId , String appSecret) {
        super(providerId, new GithubServiceProvider(appId,appSecret), new GithubAdapter());
    }
}