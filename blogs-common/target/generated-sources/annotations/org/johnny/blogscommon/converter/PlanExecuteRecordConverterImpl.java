package org.johnny.blogscommon.converter;

import javax.annotation.Generated;
import org.johnny.blogscommon.entity.plan.PlanExecuteRecord;
import org.johnny.blogscommon.utils.DateMapper;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordVo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-09T10:52:25+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"
)
public class PlanExecuteRecordConverterImpl implements PlanExecuteRecordConverter {

    private final DateMapper dateMapper = new DateMapper();

    @Override
    public PlanExecuteRecordVo domain2vo(PlanExecuteRecord planExecuteRecord) {
        if ( planExecuteRecord == null ) {
            return null;
        }

        PlanExecuteRecordVo planExecuteRecordVo = new PlanExecuteRecordVo();

        planExecuteRecordVo.setCreateDate( dateMapper.asString( planExecuteRecord.getCreateDate() ) );
        planExecuteRecordVo.setId( planExecuteRecord.getId() );
        planExecuteRecordVo.setPlanId( planExecuteRecord.getPlanId() );
        planExecuteRecordVo.setTakeTime( planExecuteRecord.getTakeTime() );
        planExecuteRecordVo.setTitle( planExecuteRecord.getTitle() );
        planExecuteRecordVo.setProgress( planExecuteRecord.getProgress() );
        planExecuteRecordVo.setJournalContent( planExecuteRecord.getJournalContent() );
        planExecuteRecordVo.setJournalShortContent( planExecuteRecord.getJournalShortContent() );
        planExecuteRecordVo.setJournalMdContent( planExecuteRecord.getJournalMdContent() );
        planExecuteRecordVo.setCreateUser( planExecuteRecord.getCreateUser() );

        return planExecuteRecordVo;
    }

    @Override
    public PlanExecuteRecord vo2domain(PlanExecuteRecordVo planExecuteRecordVo) {
        if ( planExecuteRecordVo == null ) {
            return null;
        }

        PlanExecuteRecord planExecuteRecord = new PlanExecuteRecord();

        planExecuteRecord.setId( planExecuteRecordVo.getId() );
        planExecuteRecord.setPlanId( planExecuteRecordVo.getPlanId() );
        planExecuteRecord.setTitle( planExecuteRecordVo.getTitle() );
        planExecuteRecord.setTakeTime( planExecuteRecordVo.getTakeTime() );
        planExecuteRecord.setProgress( planExecuteRecordVo.getProgress() );
        planExecuteRecord.setJournalContent( planExecuteRecordVo.getJournalContent() );
        planExecuteRecord.setJournalShortContent( planExecuteRecordVo.getJournalShortContent() );
        planExecuteRecord.setJournalMdContent( planExecuteRecordVo.getJournalMdContent() );
        planExecuteRecord.setCreateDate( dateMapper.asDate( planExecuteRecordVo.getCreateDate() ) );
        planExecuteRecord.setCreateUser( planExecuteRecordVo.getCreateUser() );

        return planExecuteRecord;
    }
}
