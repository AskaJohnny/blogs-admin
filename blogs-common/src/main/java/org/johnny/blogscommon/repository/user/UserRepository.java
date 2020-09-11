package org.johnny.blogscommon.repository.user;

import org.johnny.blogscommon.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户 Repository
 *
 * @author johnny
 * @create 2020-07-10 下午4:07
 **/
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByUsername(String username);
}