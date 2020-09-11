package org.johnny.blogscommon.service;


import org.johnny.blogscommon.vo.blog.BlogTypeVo;

import java.util.List;

/**
 * @author johnny
 * @create 2019-11-23 下午8:30
 **/
public interface BlogTypeService {

    List<BlogTypeVo> findList();

    List<BlogTypeVo> findAllBlogTypeVoList();
}