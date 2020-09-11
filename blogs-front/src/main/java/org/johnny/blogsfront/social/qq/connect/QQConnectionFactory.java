package org.johnny.blogsfront.social.qq.connect;

import org.johnny.blogsfront.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * QQ的ConnectionFactory
 *
 * @author johnny
 * @create 2019-12-21 下午12:30
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {


    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     */
    public QQConnectionFactory(String providerId,String appId ,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}