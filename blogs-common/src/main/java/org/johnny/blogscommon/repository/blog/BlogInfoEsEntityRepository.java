package org.johnny.blogscommon.repository.blog;

import org.johnny.blogscommon.entity.blog.BlogInfoEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author johnny
 * @create 2019-12-01 上午11:53
 **/

public interface BlogInfoEsEntityRepository extends ElasticsearchRepository<BlogInfoEsEntity, String> {


}