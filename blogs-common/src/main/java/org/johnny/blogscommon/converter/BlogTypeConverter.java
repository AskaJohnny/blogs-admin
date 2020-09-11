package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.blog.BlogType;
import org.johnny.blogscommon.vo.blog.BlogTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * BlogType Converter
 *
 * @author johnny
 * @create 2019-11-08 上午11:20
 **/
@Mapper
public interface BlogTypeConverter {

    BlogTypeConverter INSTANCE = Mappers.getMapper(BlogTypeConverter.class);

    BlogTypeVo domain2vo(BlogType blogType);

}