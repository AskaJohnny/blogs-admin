package org.johnny.blogscommon.vo.plan;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划的 统计 Chart 数据
 *
 * @author johnny
 * @create 2020-08-18 下午2:22
 **/
@Data
public class PlanExecuteRecordChartVo {

    /**
     * 近7天的 耗时
     */
    List<Integer> consumeDateList = new ArrayList<>();

    List<String> dateList = new ArrayList<>();

    /**
     * 计划的  天数类型 数据
     */
    List<PlanTimeType> planTimeTypeList = new ArrayList<>();

    @Data
    public static class  PlanTimeType{
        private String name;
        private Long value;

        private String code;
    }
}