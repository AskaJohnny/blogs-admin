package org.johnny.blogscommon.repository.blog;

import org.johnny.blogscommon.entity.blog.BlogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author johnny
 * @create 2019-11-23 下午8:32
 **/
@Repository
public interface BlogTypeRepository extends JpaRepository<BlogType, Integer> {


}