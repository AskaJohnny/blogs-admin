package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.blog.BlogLeaveWord;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.leaveword.BlogLeaveWordVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * BlogLeaveword Converter
 *
 * @author johnny
 * @create 2019-12-19 下午 10:20
 **/
@Mapper(uses = DateMapper.class )
public interface BlogLeaveWordConverter {

    BlogLeaveWordConverter INSTANCE = Mappers.getMapper(BlogLeaveWordConverter.class);

    @Mappings({
            @Mapping(source = "createTime" , target = "createTime" , dateFormat = "yyyy-MM-dd"),
    })
    BlogLeaveWordVo domain2vo(BlogLeaveWord blogLeaveWord);



    BlogLeaveWord vo2domain(BlogLeaveWordVo blogLeaveWordVo);

}