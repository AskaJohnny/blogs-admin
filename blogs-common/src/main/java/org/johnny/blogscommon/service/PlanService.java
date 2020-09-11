package org.johnny.blogscommon.service;

import org.johnny.blogscommon.form.PlanInfoForm;
import org.johnny.blogscommon.vo.plan.PlanChartVo;
import org.johnny.blogscommon.vo.plan.PlanVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * plan service
 *
 * @author johnny
 * @create 2020-08-17 下午6:30
 **/

public interface PlanService {

    Page<PlanVo> findPage(Pageable initPage, PlanInfoForm planInfoForm);

    PlanChartVo findPlanChartInfo();

    void addPlanInfo(PlanVo planVo);
}