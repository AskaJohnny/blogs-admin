package org.johnny.blogscommon.vo.leaveword;

import lombok.Data;
import lombok.experimental.Accessors;
import org.johnny.blogscommon.vo.blog.BlogUserInfoVo;

/**
 * @author johnny
 * @create 2019-12-19 下午5:24
 **/
@Data
@Accessors(chain = true)
public class BlogLeaveWordVo {


    private Long id;

    /**
     * markdown 的 md 格式语法内容
     */
    private String leaveWordMdContent;


    /**
     * markdown 的 html 内容
     */
    private String leaveWordHtmlContent;

    /**
     * 创建时间，发表时间
     */
    private String createTime;

    /**
     * 关联的用户ID
     */
    private Long blogUserId;

    /**
     * 这条留言是 哪个 blog下面的
     */
    private Long blogInfoId;

    /**
     * 指定该条留言 关联的 哪个留言的回复 的id
     */
    private Long parentLeaveWordId;

    /**
     * 属于哪个人的留言
     */
    private BlogUserInfoVo blogUserInfoVo;

    /**
     * 发布时间： 1分钟前 这种
     */
    private String publishTime;
}
