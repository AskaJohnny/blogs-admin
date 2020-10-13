package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:16:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class BlogInfoConverterImpl implements BlogInfoConverter {

    private final DateMapper dateMapper = new DateMapper();

    @Override
    public BlogInfoVo domain2vo(BlogInfo blogInfo) {
        if ( blogInfo == null ) {
            return null;
        }

        BlogInfoVo blogInfoVo = new BlogInfoVo();

        blogInfoVo.setCreateTime( dateMapper.asString( blogInfo.getCreateTime() ) );
        blogInfoVo.setCreateDate( dateMapper.asString( blogInfo.getCreateDate() ) );
        blogInfoVo.setId( blogInfo.getId() );
        blogInfoVo.setBlogTitle( blogInfo.getBlogTitle() );
        blogInfoVo.setBlogContent( blogInfo.getBlogContent() );
        blogInfoVo.setBlogShortContent( blogInfo.getBlogShortContent() );
        blogInfoVo.setBlogMdContent( blogInfo.getBlogMdContent() );
        if ( blogInfo.getBlogTypeId() != null ) {
            blogInfoVo.setBlogTypeId( String.valueOf( blogInfo.getBlogTypeId() ) );
        }
        blogInfoVo.setBlogImageUrl( blogInfo.getBlogImageUrl() );
        blogInfoVo.setUpdateTime( dateMapper.asString( blogInfo.getUpdateTime() ) );
        blogInfoVo.setCreateMonth( blogInfo.getCreateMonth() );
        blogInfoVo.setCreateUser( blogInfo.getCreateUser() );
        blogInfoVo.setClickCount( blogInfo.getClickCount() );
        blogInfoVo.setThumbCount( blogInfo.getThumbCount() );

        return blogInfoVo;
    }

    @Override
    public BlogInfo vo2domain(BlogInfoVo blogInfoVo) {
        if ( blogInfoVo == null ) {
            return null;
        }

        BlogInfo blogInfo = new BlogInfo();

        blogInfo.setId( blogInfoVo.getId() );
        blogInfo.setBlogTitle( blogInfoVo.getBlogTitle() );
        blogInfo.setBlogContent( blogInfoVo.getBlogContent() );
        blogInfo.setBlogShortContent( blogInfoVo.getBlogShortContent() );
        blogInfo.setBlogMdContent( blogInfoVo.getBlogMdContent() );
        if ( blogInfoVo.getBlogTypeId() != null ) {
            blogInfo.setBlogTypeId( Integer.parseInt( blogInfoVo.getBlogTypeId() ) );
        }
        blogInfo.setBlogImageUrl( blogInfoVo.getBlogImageUrl() );
        blogInfo.setCreateTime( dateMapper.asDate( blogInfoVo.getCreateTime() ) );
        blogInfo.setCreateDate( dateMapper.asDate( blogInfoVo.getCreateDate() ) );
        blogInfo.setUpdateTime( dateMapper.asDate( blogInfoVo.getUpdateTime() ) );
        blogInfo.setCreateUser( blogInfoVo.getCreateUser() );
        blogInfo.setClickCount( blogInfoVo.getClickCount() );
        blogInfo.setCreateMonth( blogInfoVo.getCreateMonth() );
        blogInfo.setThumbCount( blogInfoVo.getThumbCount() );

        return blogInfo;
    }
}
