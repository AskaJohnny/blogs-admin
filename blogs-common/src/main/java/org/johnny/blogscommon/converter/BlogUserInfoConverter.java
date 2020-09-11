package org.johnny.blogscommon.converter;


import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.blog.BlogUserInfoVo;
import org.johnny.blogscommon.vo.social.github.GithubUserInfo;
import org.johnny.blogscommon.vo.social.qq.QQUserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * BlogUserInfo Converter
 *
 * @author johnny
 * @create 2019-11-08 上午11:20
 **/
@Mapper(uses = DateMapper.class)
public interface BlogUserInfoConverter {

    BlogUserInfoConverter INSTANCE = Mappers.getMapper(BlogUserInfoConverter.class);

    BlogUserInfoVo domain2vo(BlogUserInfo blogUserInfo);

    BlogUserInfo vo2domain(BlogUserInfoVo blogUserInfoVo);

    @Mappings({
            @Mapping(source = "nickname", target = "userName"),
            @Mapping(source = "figureurl_qq_1", target = "headImage")
    })
    BlogUserInfo qq2domain(QQUserInfo qqUserInfo);


    @Mappings({
            @Mapping(source = "login", target = "userName"),
            @Mapping(source = "avatar_url", target = "headImage"),
            @Mapping(source = "html_url", target = "profileUrl"),
            @Mapping(ignore = true , target = "id")
    })
    BlogUserInfo github2domain(GithubUserInfo githubUserInfo);

}