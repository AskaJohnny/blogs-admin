package org.johnny.blogscommon.repository.system;

import org.johnny.blogscommon.entity.system.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Menu的 Repository
 *
 * @author johnny
 * @create 2020-07-10 下午2:16
 **/
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {


    List<MenuEntity> findByParentIdIsNullOrderBySortOrderAsc();

    List<MenuEntity> findByParentIdOrderBySortOrderAsc(Long parentId);
    List<MenuEntity> findByParentIdAndTypeIsNotOrderBySortOrderAsc(Long parentId,Integer type);
}