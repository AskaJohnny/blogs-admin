package org.johnny.blogscommon.repository.system;

import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.entity.system.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色 管理 Repository
 *
 * @author johnny
 * @create 2020-07-13 下午1:47
 **/
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


    List<RoleEntity> findByIdIn(List<Long> roleIdList);

}