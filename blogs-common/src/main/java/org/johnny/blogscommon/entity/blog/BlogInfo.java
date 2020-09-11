package org.johnny.blogscommon.entity.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Blog实体类
 *
 * @author johnny
 * @create 2019-11-23 下午6:51
 **/
@Data
@Entity

@EntityListeners(AuditingEntityListener.class)
public class BlogInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 博客标题
     */
    @Field(analyzer = "ik_max_word"  , type = FieldType.Text , searchAnalyzer = "ik_max_word")
    private String blogTitle;
    /**
     * 博客内容
     */
    @Column(columnDefinition = "text")
    private String blogContent;

    /**
     * 博客简要
     */
    private String blogShortContent;

    /**
     * 博客 MD 的内容
     */
    @Column(columnDefinition = "text")
    private String blogMdContent;

    /**
     * 博客类型ID
     */
    private Integer blogTypeId;

    /**
     * 博客 图片
     */
    private String blogImageUrl;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;

    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 点击次数
     */
    private Long clickCount;

    private String createMonth;

    /**
     * 点赞数
     */
    private Long thumbCount;

    /**
     * 锚点
     */
    @Column(columnDefinition = "text")
    private String anchorJson;



}