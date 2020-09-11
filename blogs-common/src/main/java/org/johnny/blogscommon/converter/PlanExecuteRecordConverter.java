package org.johnny.blogscommon.converter;

import org.johnny.blogscommon.entity.plan.Plan;
import org.johnny.blogscommon.entity.plan.PlanExecuteRecord;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordVo;
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
public interface PlanExecuteRecordConverter {


    PlanExecuteRecordConverter INSTANCE = Mappers.getMapper(PlanExecuteRecordConverter.class);

    @Mappings({
            @Mapping(source = "createDate", target = "createDate" ,dateFormat = "yyyy-MM-dd"),
    })
    PlanExecuteRecordVo domain2vo(PlanExecuteRecord planExecuteRecord);


    PlanExecuteRecord vo2domain(PlanExecuteRecordVo planExecuteRecordVo);
}