package org.johnny.blogscommon.repository.system;

import org.johnny.blogscommon.entity.system.MenuEntity;
import org.johnny.blogscommon.entity.system.UrlPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Url Permission Repository
 *
 * @author johnny
 * @create 2020-07-10 下午2:16
 **/
public interface UrlPermissionRepository extends JpaRepository<UrlPermission, Long> {


}