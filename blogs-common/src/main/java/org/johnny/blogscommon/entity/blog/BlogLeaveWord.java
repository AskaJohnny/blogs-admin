package org.johnny.blogscommon.entity.blog;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 博客留言信息
 *
 * @author johnny
 * @create 2019-12-19 下午4:47
 **/
@Data
@Entity
public class BlogLeaveWord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * markdown 的 md 格式语法内容
     */
    @Column(columnDefinition = "text")
    private String leaveWordMdContent;


    /**
     * markdown 的 html 内容
     */
    @Column(columnDefinition = "text")
    private String leaveWordHtmlContent;

    /**
     * 创建时间，发表时间
     */
    private Date createTime;

    /**
     * 关联的用户ID
     */
    private Long blogUserId;

    /**
     * 指定该条留言 关联的 哪个留言的回复 的id
     */
    private Long parentLeaveWordId;

    /**
     * 指定 这个留言属于哪个 blogInfo的
     */
    private Long blogInfoId;





}