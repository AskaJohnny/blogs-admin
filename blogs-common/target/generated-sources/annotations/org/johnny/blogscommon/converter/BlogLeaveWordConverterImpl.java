package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.blog.BlogLeaveWord;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.leaveword.BlogLeaveWordVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:16:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class BlogLeaveWordConverterImpl implements BlogLeaveWordConverter {

    private final DateMapper dateMapper = new DateMapper();

    @Override
    public BlogLeaveWordVo domain2vo(BlogLeaveWord blogLeaveWord) {
        if ( blogLeaveWord == null ) {
            return null;
        }

        BlogLeaveWordVo blogLeaveWordVo = new BlogLeaveWordVo();

        blogLeaveWordVo.setCreateTime( dateMapper.asString( blogLeaveWord.getCreateTime() ) );
        blogLeaveWordVo.setId( blogLeaveWord.getId() );
        blogLeaveWordVo.setLeaveWordMdContent( blogLeaveWord.getLeaveWordMdContent() );
        blogLeaveWordVo.setLeaveWordHtmlContent( blogLeaveWord.getLeaveWordHtmlContent() );
        blogLeaveWordVo.setBlogUserId( blogLeaveWord.getBlogUserId() );
        blogLeaveWordVo.setBlogInfoId( blogLeaveWord.getBlogInfoId() );
        blogLeaveWordVo.setParentLeaveWordId( blogLeaveWord.getParentLeaveWordId() );

        return blogLeaveWordVo;
    }

    @Override
    public BlogLeaveWord vo2domain(BlogLeaveWordVo blogLeaveWordVo) {
        if ( blogLeaveWordVo == null ) {
            return null;
        }

        BlogLeaveWord blogLeaveWord = new BlogLeaveWord();

        blogLeaveWord.setId( blogLeaveWordVo.getId() );
        blogLeaveWord.setLeaveWordMdContent( blogLeaveWordVo.getLeaveWordMdContent() );
        blogLeaveWord.setLeaveWordHtmlContent( blogLeaveWordVo.getLeaveWordHtmlContent() );
        blogLeaveWord.setCreateTime( dateMapper.asDate( blogLeaveWordVo.getCreateTime() ) );
        blogLeaveWord.setBlogUserId( blogLeaveWordVo.getBlogUserId() );
        blogLeaveWord.setParentLeaveWordId( blogLeaveWordVo.getParentLeaveWordId() );
        blogLeaveWord.setBlogInfoId( blogLeaveWordVo.getBlogInfoId() );

        return blogLeaveWord;
    }
}
