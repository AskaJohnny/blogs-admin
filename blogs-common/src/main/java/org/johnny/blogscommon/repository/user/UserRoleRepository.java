package org.johnny.blogscommon.repository.user;

import org.johnny.blogscommon.entity.user.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户角色 Repository
 *
 * @author johnny
 * @create 2020-07-14 上午9:13
 **/
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Long> {


    List<UserRoleEntity> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}