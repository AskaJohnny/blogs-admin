package org.johnny.blogscommon.service.impl;

import org.elasticsearch.index.query.QueryBuilders;
import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.entity.blog.BlogInfoEsEntity;
import org.johnny.blogscommon.repository.blog.BlogInfoEsEntityRepository;
import org.johnny.blogscommon.repository.blog.BlogInfoRepository;
import org.johnny.blogscommon.service.BlogInfoEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author johnny
 * @create 2019-12-01 上午11:54
 **/
@Service
public class BlogInfoEsServiceImpl implements BlogInfoEsService {

    /**
     * 为了让 blogs-server项目 不依赖
     */
    @Autowired(required = false)
    private BlogInfoEsEntityRepository blogInfoEsEntityRepository;
    /**
     * 为了让 blogs-server项目 不依赖
     */
    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private BlogInfoRepository blogInfoRepository;


    @Override
    public BlogInfoEsEntity save(BlogInfoEsEntity blogInfoEsEntity) {
        return blogInfoEsEntityRepository.save(blogInfoEsEntity);
    }

    @Override
    public void delete(String id) {
        blogInfoEsEntityRepository.deleteById(id);
    }

    @Override
    public BlogInfoEsEntity update(BlogInfoEsEntity blogInfoEsEntity) {
        return blogInfoEsEntityRepository.save(blogInfoEsEntity);
    }


    @Override
    public List<BlogInfoEsEntity> queryBlogFromEs(String blogTitle) {
        List<BlogInfoEsEntity> blogInfoEsEntitys = null;
        Pageable pageable = PageRequest.of(0, 100);
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder().withPageable(pageable);
        if (!StringUtils.isEmpty(blogTitle)) {
            query.withQuery(QueryBuilders.matchQuery("blogTitle", blogTitle));
            NativeSearchQuery nativeSearchQuery = query.build();
            blogInfoEsEntitys = elasticsearchTemplate.queryForList(nativeSearchQuery, BlogInfoEsEntity.class);
        }
        return blogInfoEsEntitys;
    }

    @Override
    public List<BlogInfo> queryBLogForSearch(String blogTitle) {


        return null;
    }


}