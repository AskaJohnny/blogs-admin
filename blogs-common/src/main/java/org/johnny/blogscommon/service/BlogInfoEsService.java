package org.johnny.blogscommon.service;


import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.entity.blog.BlogInfoEsEntity;

import java.util.List;

/**
 * @author johnny
 * @create 2019-12-01 上午11:54
 **/
public interface BlogInfoEsService {

     BlogInfoEsEntity save(BlogInfoEsEntity blogInfoEsEntity);


     void delete(String id);

    BlogInfoEsEntity update(BlogInfoEsEntity blogInfoEsEntity);

     List<BlogInfoEsEntity> queryBlogFromEs(String blogTitle);


     List<BlogInfo> queryBLogForSearch(String blogTitle);
}