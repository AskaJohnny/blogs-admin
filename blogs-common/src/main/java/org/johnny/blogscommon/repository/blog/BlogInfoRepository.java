package org.johnny.blogscommon.repository.blog;

import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blogInfo repository
 *
 * @author johnny
 * @create 2019-11-23 下午7:07
 **/
@Repository
public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {

    List<BlogInfo> findByBlogTypeId(Integer typeId);

}