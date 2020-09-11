package org.johnny.blogscommon.service;


import org.johnny.blogscommon.form.PlanExecuteRecordInfoForm;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordChartVo;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * plan service
 *
 * @author johnny
 * @create 2020-08-17 下午6:30
 **/

public interface PlanExecuteRecordService {

    Page<PlanExecuteRecordVo> findPage(Pageable initPage, PlanExecuteRecordInfoForm planDetailInfoForm);

    PlanExecuteRecordChartVo findPlanExecuteRecordChartInfo(Long id);

    void addPlanExecuteRecord(PlanExecuteRecordVo planExecuteRecordVo);
}