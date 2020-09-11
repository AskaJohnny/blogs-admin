package org.johnny.blogscommon.converter;


import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * BlogInfo Converter
 *
 * @author johnny
 * @create 2019-11-08 上午11:20
 **/
@Mapper(uses = DateMapper.class)

public interface BlogInfoConverter {

    BlogInfoConverter INSTANCE = Mappers.getMapper(BlogInfoConverter.class);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate" ,dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "createTime" , target = "createTime" , dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "anchors" , ignore = true)
    })
    BlogInfoVo domain2vo(BlogInfo blogInfo);

    BlogInfo vo2domain(BlogInfoVo blogInfoVo);

}