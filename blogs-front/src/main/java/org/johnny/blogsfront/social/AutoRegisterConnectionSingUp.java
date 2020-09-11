package org.johnny.blogsfront.social;


import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.converter.BlogUserInfoConverter;
import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.johnny.blogscommon.repository.blog.BlogUserInfoRepository;
import org.johnny.blogscommon.vo.social.github.GithubUserInfo;
import org.johnny.blogscommon.vo.social.qq.QQUserInfo;
import org.johnny.blogsfront.social.github.api.Github;
import org.johnny.blogsfront.social.qq.api.QQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 自动注册逻辑
 *
 * @author johnny
 * @create 2019-12-21 下午4:10
 **/
@Slf4j
@Component
public class AutoRegisterConnectionSingUp implements ConnectionSignUp {


    @Autowired
    private BlogUserInfoRepository blogUserInfoRepository;

    @Override
    public String execute(Connection<?> connection) {
        BlogUserInfo blogUserInfo = null;

        ConnectionKey connectionKey = connection.getKey();
        String providerId = connectionKey.getProviderId();
        if(providerId.equalsIgnoreCase("github")){
            Connection<Github> githubConnection = (Connection<Github>) connection;
            Github github =  githubConnection.getApi();
            GithubUserInfo githubUserInfo = github.getExistUserInfo();
            log.info("registerQQUserInfo：{}"  , githubUserInfo);
            blogUserInfo = BlogUserInfoConverter.INSTANCE.github2domain(githubUserInfo);

            blogUserInfoRepository.save(blogUserInfo);
            log.info("blogUserInfo：{} "  ,blogUserInfo);

        }else if(providerId.equalsIgnoreCase("qq")){
            //应该根据本地是否 有这个用户 如果没有再进行保存，这里好像没做
            Connection<QQ> qqConnection = (Connection<QQ>) connection;
            QQ qq =  qqConnection.getApi();
            QQUserInfo qqUserInfo = qq.getExistUserInfo();
            log.info("registerQQUserInfo：{}"  , qqUserInfo);
            blogUserInfo = BlogUserInfoConverter.INSTANCE.qq2domain(qqUserInfo);

            blogUserInfoRepository.save(blogUserInfo);
            log.info("blogUserInfo：{} "  ,blogUserInfo);
        }

        return blogUserInfo.getId().toString();
    }
}