package org.johnny.blogscommon.vo.plan;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author johnny
 * @create 2020-08-18 下午5:26
 **/
@Data
public class PlanChartVo {

    List<String> typeList = new ArrayList<>();

    /**
     * 计划的  天数类型 数据
     */
    List<PlanType> planTypeList = new ArrayList<>();


    @Data
    public static class  PlanType{
        private String name;
        private Long value;

        private String code;
    }
}