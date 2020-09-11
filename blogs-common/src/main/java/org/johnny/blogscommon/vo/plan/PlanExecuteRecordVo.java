package org.johnny.blogscommon.vo.plan;

import lombok.Data;
import org.johnny.blogscommon.vo.common.BaseVo;

/**
 * @author johnny
 * @create 2020-08-16 下午6:29
 **/
@Data
public class PlanExecuteRecordVo {

    private Long id;


    private Long planId;

    /**
     * 花费时间
     */
    private Integer takeTime;
    /**
     * 标题  2020-08-19 / 2020-08-20 偷懒 。。这种
     */
    private String title;

    /**
     * 执行进度 当天的
     */
    private String progress;

    /**
     * 博客内容
     */
    private String journalContent;

    /**
     * 博客简要
     */
    private String journalShortContent;

    /**
     * 博客 MD 的内容
     */
    private String journalMdContent;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 创建人
     */
    private String createUser;
}