package org.johnny.blogscommon.repository.system;

import org.johnny.blogscommon.entity.system.RoleUrlPermission;
import org.johnny.blogscommon.entity.system.UrlPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Url Permission Repository
 *
 * @author johnny
 * @create 2020-07-10 下午2:16
 **/
public interface RoleUrlPermissionRepository extends JpaRepository<RoleUrlPermission, Long> {


}