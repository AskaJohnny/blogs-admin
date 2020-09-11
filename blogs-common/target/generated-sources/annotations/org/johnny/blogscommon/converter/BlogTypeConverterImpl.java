package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.blog.BlogType;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-09T10:52:25+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class BlogTypeConverterImpl implements BlogTypeConverter {

    @Override
    public BlogTypeVo domain2vo(BlogType blogType) {
        if ( blogType == null ) {
            return null;
        }

        BlogTypeVo blogTypeVo = new BlogTypeVo();

        blogTypeVo.setId( blogType.getId() );
        blogTypeVo.setTypeName( blogType.getTypeName() );
        blogTypeVo.setBlogTypeAnchor( blogType.getBlogTypeAnchor() );

        return blogTypeVo;
    }
}
