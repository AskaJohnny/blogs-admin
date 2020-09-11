package org.johnny.blogsfront.controller;


import org.johnny.blogscommon.form.PlanInfoForm;
import org.johnny.blogscommon.service.PlanService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.plan.PlanChartVo;
import org.johnny.blogscommon.vo.plan.PlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 计划 Controller
 *
 * @author johnny
 * @create 2020-08-16 下午6:26
 **/

@RestController
@RequestMapping("/plan")
public class PlanController {


    @Autowired
    private PlanService planService;

    /**
     * 查询blogInfo信息
     *
     * @return : ResultVo
     */
    @PostMapping("/page")
    @CrossOrigin
    public ResultVo<Page<PlanVo>> page(@RequestBody PlanInfoForm planInfoForm) {
        Page<PlanVo> page = planService.findPage(PageUtil.initPage(planInfoForm), planInfoForm);
        return ResultVoUtil.success(page);
    }


    @GetMapping("/charts")
    public ResultVo findPlanExecuteRecordChartInfo() {

        /**
         * 每个 类型的 天数
         * ["学习", "健康", "素质"]
         * [
         *               { value: 220, name: "剩余天数" },
         *               { value: 2, name: "执行天数" },
         *               { value: 0, name: "偷懒天数" },
         *               { value: 0, name: "特殊天数" },
         *  ]
         */
        PlanChartVo planChartVo = planService.findPlanChartInfo();
        return ResultVoUtil.success(planChartVo);
    }


}