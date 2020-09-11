package org.johnny.blogscommon.repository.blog;


import org.johnny.blogscommon.entity.blog.BlogUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Blog的 用户信息
 *
 * @author johnny
 * @create 2019-12-21 下午4:32
 **/
@Repository
public interface BlogUserInfoRepository extends JpaRepository<BlogUserInfo, Long> {
}