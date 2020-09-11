package org.johnny.blogscommon.repository.blog;

import org.johnny.blogscommon.entity.blog.BlogLeaveWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * blogInfo repository
 * @author johnny
 * @create 2019-12-19 下午10:36
 **/
@Repository
public interface BlogLeaveWordRepository extends JpaRepository<BlogLeaveWord, Long> {



}