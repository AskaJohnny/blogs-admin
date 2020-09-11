package org.johnny.blogscommon.entity.plan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 计划执行的 记录
 *
 * @author johnny
 * @create 2020-08-16 下午6:08
 **/
@Data
@Entity
public class PlanExecuteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private Long planId;

    /**
     * 标题  2020-08-19 / 2020-08-20 偷懒 。。这种
     */
    private String title;

    /**
     * 花费时间
     */
    private Integer takeTime;

    /**
     * 执行进度 当天的
     */
    private String progress;


    /**
     * 博客内容
     */
    @Column(columnDefinition = "text")
    private String journalContent;

    /**
     * 博客简要
     */
    private String journalShortContent;

    /**
     * 博客 MD 的内容
     */
    @Column(columnDefinition = "text")
    private String journalMdContent;

    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    private Date createDate;

    /**
     * 创建人
     */
    private String createUser;


    /**
     * 特殊状态  偷懒状态  执行状态
     */
    private String status;




}