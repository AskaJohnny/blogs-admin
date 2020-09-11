package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.entity.plan.Plan;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.blog.BlogInfoVo;
import org.johnny.blogscommon.vo.plan.PlanVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author johnny
 * @create 2020-08-17 下午7:54
 **/
@Mapper(uses = DateMapper.class)
public interface PlanConverter {


    PlanConverter INSTANCE = Mappers.getMapper(PlanConverter.class);

    @Mappings({
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd"),
            @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd"),
    })
    PlanVo domain2vo(Plan plan);

    Plan vo2domain(PlanVo planVo);



}