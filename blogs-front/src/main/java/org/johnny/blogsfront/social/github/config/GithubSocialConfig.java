package org.johnny.blogsfront.social.github.config;

import org.johnny.blogsfront.social.github.connect.GithubConnectionFactory;
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
public class GithubSocialConfig extends SocialConfigurerAdapter {


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createGithubConnectionFactory());
    }

    private ConnectionFactory<?> createGithubConnectionFactory() {
        return new GithubConnectionFactory("github" , "b28eb51f9f9cb8592e8b","9d05fd5c5a2d3024ade8d617fbaf987ab1990246");
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