package org.johnny.blogscommon.entity.blog;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 博客类型
 *
 * @author johnny
 * @create 2019-11-23 下午6:56
 **/
@Data
@Entity
public class BlogType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 类型描述
     */
    private String typeDesc;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 锚点str
     */
    private String blogTypeAnchor;
}