package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.plan.Plan;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.plan.PlanVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T16:16:35+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class PlanConverterImpl implements PlanConverter {

    private final DateMapper dateMapper = new DateMapper();

    @Override
    public PlanVo domain2vo(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        PlanVo planVo = new PlanVo();

        planVo.setStartTime( dateMapper.asString( plan.getStartTime() ) );
        planVo.setEndTime( dateMapper.asString( plan.getEndTime() ) );
        planVo.setCreateTime( dateMapper.asString( plan.getCreateTime() ) );
        planVo.setId( plan.getId() );
        planVo.setCreateBy( plan.getCreateBy() );
        planVo.setUpdateBy( plan.getUpdateBy() );
        planVo.setUpdateTime( dateMapper.asString( plan.getUpdateTime() ) );
        planVo.setDelFlag( plan.getDelFlag() );
        planVo.setPlanName( plan.getPlanName() );
        planVo.setPlanDesc( plan.getPlanDesc() );
        planVo.setPlanImage( plan.getPlanImage() );
        planVo.setTarget( plan.getTarget() );
        planVo.setAward( plan.getAward() );
        planVo.setProgress( plan.getProgress() );
        planVo.setStatus( plan.getStatus() );

        return planVo;
    }

    @Override
    public Plan vo2domain(PlanVo planVo) {
        if ( planVo == null ) {
            return null;
        }

        Plan plan = new Plan();

        plan.setId( planVo.getId() );
        plan.setCreateBy( planVo.getCreateBy() );
        plan.setCreateTime( dateMapper.asDate( planVo.getCreateTime() ) );
        plan.setUpdateBy( planVo.getUpdateBy() );
        plan.setUpdateTime( dateMapper.asDate( planVo.getUpdateTime() ) );
        plan.setDelFlag( planVo.getDelFlag() );
        plan.setPlanName( planVo.getPlanName() );
        plan.setPlanDesc( planVo.getPlanDesc() );
        plan.setPlanImage( planVo.getPlanImage() );
        plan.setStartTime( dateMapper.asDate( planVo.getStartTime() ) );
        plan.setEndTime( dateMapper.asDate( planVo.getEndTime() ) );
        plan.setTarget( planVo.getTarget() );
        plan.setAward( planVo.getAward() );
        plan.setProgress( planVo.getProgress() );
        plan.setStatus( planVo.getStatus() );

        return plan;
    }
}
