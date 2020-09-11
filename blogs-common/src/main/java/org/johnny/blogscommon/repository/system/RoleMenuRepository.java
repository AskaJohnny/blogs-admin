package org.johnny.blogscommon.repository.system;

import org.johnny.blogscommon.entity.system.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色 菜单 管理 Repository
 *
 * @author johnny
 * @create 2020-07-13 下午1:47
 **/
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity, Long> {


    List<RoleMenuEntity> findByRoleId(Long roleId);

    List<RoleMenuEntity> findByMenuId(Long menuId);

    List<RoleMenuEntity> findByRoleIdIn(List<Long> roleIds);

    void deleteByRoleId(Long roleId);

}