package org.johnny.blogscommon.repository.thumb;

import org.johnny.blogscommon.entity.blog.BlogInfo;
import org.johnny.blogscommon.entity.thumb.UserThumbRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author johnny
 * @create 2020-12-21 11:09 上午
 **/
public interface UserThumbRelationRepository extends JpaRepository<UserThumbRelation, Long> {

    UserThumbRelation findByUserIdAndBlogInfoId(Long userId, Long id);
}