package org.johnny.blogscommon.entity.plan;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 计划 Entity
 *
 * @author johnny
 * @create 2020-08-16 下午5:57
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Plan extends BaseEntity {


    private String planName;


    private String planDesc;


    private String planImage;

    @CreatedDate
    private Date startTime;

    @CreatedDate
    private Date endTime;

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
    private String planType;


}