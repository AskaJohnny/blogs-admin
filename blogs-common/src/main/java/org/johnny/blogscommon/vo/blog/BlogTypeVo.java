package org.johnny.blogscommon.vo.blog;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author johnny
 * @create 2019-11-23 下午8:31
 **/
@Data
@Accessors(chain = true)
public class BlogTypeVo {

    private Integer id;

    /**
     * 类型名称
     */
    private String typeName;

    private List<BlogInfoVo> blogInfoVoList;

    private String blogTypeAnchor;
}