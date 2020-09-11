package org.johnny.blogsserver.controller.plan;


import org.johnny.blogscommon.form.PlanExecuteRecordInfoForm;
import org.johnny.blogscommon.service.PlanExecuteRecordService;
import org.johnny.blogscommon.service.PlanService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordChartVo;
import org.johnny.blogscommon.vo.plan.PlanExecuteRecordVo;
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
@RequestMapping("/planExecuteRecord")
public class PlanExecuteRecordController {


    @Autowired
    private PlanService planService;

    @Autowired
    private PlanExecuteRecordService planExecuteRecordService;

    /**
     * 查询blogInfo信息
     *
     * @return : ResultVo
     */
    @PostMapping("/page")
    @CrossOrigin
    public ResultVo<Page<PlanVo>> page(@RequestBody PlanExecuteRecordInfoForm planDetailInfoForm) {
        Page<PlanExecuteRecordVo> page = planExecuteRecordService.findPage(PageUtil.initPage(planDetailInfoForm), planDetailInfoForm);
        return ResultVoUtil.success(page);
    }



    @CrossOrigin
    @PostMapping("/saveExecuteRecordInfo")
    public ResultVo saveExecuteRecordInfo(@RequestBody PlanExecuteRecordVo planExecuteRecordVo) {
        planExecuteRecordService.addPlanExecuteRecord(planExecuteRecordVo);
        return ResultVoUtil.success();
    }


    @GetMapping("/charts/{id}")
    public ResultVo findPlanExecuteRecordChartInfo(@PathVariable(value = "id") Long id) {

        /**
         * 每个 类型的 天数
         * [
         *               { value: 220, name: "剩余天数" },
         *               { value: 2, name: "执行天数" },
         *               { value: 0, name: "偷懒天数" },
         *               { value: 0, name: "特殊天数" },
         *  ]
         */

        /**
         * 最近7 天的耗时 [60, 30, 55, 0, 30, 70, 65]
         */
        PlanExecuteRecordChartVo planChartVo = planExecuteRecordService.findPlanExecuteRecordChartInfo(id);
        return ResultVoUtil.success(planChartVo);
    }
}