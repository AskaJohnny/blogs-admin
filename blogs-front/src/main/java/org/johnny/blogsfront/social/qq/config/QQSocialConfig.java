package org.johnny.blogsfront.social.qq.config;

import org.johnny.blogsfront.social.qq.connect.QQConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * QQ的社交登录配置
 *
 * @author johnny
 * @create 2019-12-21 下午12:42
 **/
@Configuration
public class QQSocialConfig extends SocialConfigurerAdapter {


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createQQConnectionFactory());
    }

    private ConnectionFactory<?> createQQConnectionFactory() {
        return new QQConnectionFactory("qq" , "101841802","cb3435edc104a10ef7bca8904c2ff4ca");
        //return new QQConnectionFactory("qq" , "101841802","cb3435edc104a10ef7bca8904c2ff4ca");
    }

    @Override
    public UserIdSource getUserIdSource() {
        return super.getUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return super.getUsersConnectionRepository(connectionFactoryLocator);
    }
}