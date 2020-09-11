package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.johnny.blogscommon.vo.blog.BlogUserInfoVo;
import org.johnny.blogscommon.vo.social.github.GithubUserInfo;
import org.johnny.blogscommon.vo.social.qq.QQUserInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-09T10:52:25+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class BlogUserInfoConverterImpl implements BlogUserInfoConverter {

    @Override
    public BlogUserInfoVo domain2vo(BlogUserInfo blogUserInfo) {
        if ( blogUserInfo == null ) {
            return null;
        }

        BlogUserInfoVo blogUserInfoVo = new BlogUserInfoVo();

        blogUserInfoVo.setId( blogUserInfo.getId() );
        blogUserInfoVo.setUserName( blogUserInfo.getUserName() );
        blogUserInfoVo.setHeadImage( blogUserInfo.getHeadImage() );

        return blogUserInfoVo;
    }

    @Override
    public BlogUserInfo vo2domain(BlogUserInfoVo blogUserInfoVo) {
        if ( blogUserInfoVo == null ) {
            return null;
        }

        BlogUserInfo blogUserInfo = new BlogUserInfo();

        blogUserInfo.setId( blogUserInfoVo.getId() );
        blogUserInfo.setUserName( blogUserInfoVo.getUserName() );
        blogUserInfo.setHeadImage( blogUserInfoVo.getHeadImage() );

        return blogUserInfo;
    }

    @Override
    public BlogUserInfo qq2domain(QQUserInfo qqUserInfo) {
        if ( qqUserInfo == null ) {
            return null;
        }

        BlogUserInfo blogUserInfo = new BlogUserInfo();

        blogUserInfo.setUserName( qqUserInfo.getNickname() );
        blogUserInfo.setHeadImage( qqUserInfo.getFigureurl_qq_1() );
        blogUserInfo.setGender( qqUserInfo.getGender() );
        blogUserInfo.setProvince( qqUserInfo.getProvince() );
        blogUserInfo.setCity( qqUserInfo.getCity() );
        if ( qqUserInfo.getYear() != null ) {
            blogUserInfo.setYear( Integer.parseInt( qqUserInfo.getYear() ) );
        }

        return blogUserInfo;
    }

    @Override
    public BlogUserInfo github2domain(GithubUserInfo githubUserInfo) {
        if ( githubUserInfo == null ) {
            return null;
        }

        BlogUserInfo blogUserInfo = new BlogUserInfo();

        blogUserInfo.setProfileUrl( githubUserInfo.getHtml_url() );
        blogUserInfo.setUserName( githubUserInfo.getLogin() );
        blogUserInfo.setHeadImage( githubUserInfo.getAvatar_url() );

        return blogUserInfo;
    }
}
