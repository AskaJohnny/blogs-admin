package org.johnny.blogscommon.vo.plan;

import lombok.Data;
import org.johnny.blogscommon.vo.common.BaseVo;

/**
 * @author johnny
 * @create 2020-08-16 下午6:29
 **/
@Data
public class PlanVo extends BaseVo {


    private String planName;


    private String planDesc;


    private String planImage;


    private String startTime;


    private String endTime;

    private String target;

    private String award;

    private String progress;

    /**
     * 未开始
     * 进行中
     * 已结束
     * 放弃
     */
    private String status;


    /**
     * 计划的类型，比如 生活 健康 学习  等等。。。
     */
    private Integer planTypeId;

}