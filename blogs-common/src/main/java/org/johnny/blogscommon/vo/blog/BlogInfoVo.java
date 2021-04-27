package org.johnny.blogscommon.vo.blog;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * blog vo对象
 *
 * @author johnny
 * @create 2019-11-23 下午7:01
 **/
@Data
@Accessors(chain = true)
public class BlogInfoVo {

    private Long id;

    /**
     * 博客标题
     */
    private String blogTitle;
    /**
     * 博客内容
     */
    private String blogContent;

    /**
     * 博客简要
     */
    private String blogShortContent;

    /**
     * 博客 MD 的内容
     */
    private String blogMdContent;
    /**
     * 博客类型ID
     */
    private String blogTypeId;

    private String blogTypeName;

    private String blogTypeAnchor;

    /**
     * 博客 图片
     */
    private String blogImageUrl;

    /**
     * 创建时间
     */
    private String createTime;

    private String updateTime;

    private String createDate;

    private String createMonth;

    private String createUser;


    /**
     * 点击次数
     */
    private Long clickCount;

    /**
     * 点赞舒
     */
    private Long thumbCount;

    private List<Anchor> anchors;



    private String previousBlogTitle;

    private Long previousBlogId;

    private String nextBlogTitle;

    private Long nextBlogId;

    private String isThumbed;


}