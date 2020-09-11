package org.johnny.blogscommon.service.system.impl;

import com.google.common.collect.Maps;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.entity.system.MenuEntity;
import org.johnny.blogscommon.entity.system.QRoleEntity;
import org.johnny.blogscommon.entity.system.QRoleMenuEntity;
import org.johnny.blogscommon.repository.system.MenuRepository;
import org.johnny.blogscommon.repository.system.RoleMenuRepository;
import org.johnny.blogscommon.service.system.MenuService;
import org.johnny.blogscommon.vo.requestvo.system.MenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.MenuResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单的Service Impl
 *
 * @author johnny
 * @create 2020-07-11 下午4:10
 **/
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public void addMenu(MenuReqVo menuReqVo) {
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(menuReqVo, menuEntity);
        menuRepository.save(menuEntity);
    }

    @Override
    public List<MenuResultVo> findAllMenuList(boolean includeButton) {
        final Integer type = includeButton ? null : 1;
        List<MenuResultVo> menuResultVoList = new ArrayList<>();
        menuRepository.findByParentIdIsNullOrderBySortOrderAsc().forEach(menuEntity -> {
            MenuResultVo menuResultVo = new MenuResultVo();
            BeanUtils.copyProperties(menuEntity, menuResultVo);
            menuResultVo.setName(menuResultVo.getLabel());
            fillChildrenMenu(menuResultVo, type);
            menuResultVoList.add(menuResultVo);
        });
        return menuResultVoList;
    }

    @Override
    public List<MenuResultVo> findAllMenuListAndButton() {
        return null;
    }

    @Override
    public void editMenu(MenuReqVo menuReqVo) {
        if (menuReqVo.getId() != null) {
            MenuEntity menuEntity = menuRepository.findById(menuReqVo.getId()).orElse(null);
            if (menuEntity != null) {
                BeanUtils.copyProperties(menuReqVo, menuEntity);
                menuRepository.save(menuEntity);
            }
        }
    }

    @Override
    public void deleteMenu(Long id) {
        if (id != null) {
            menuRepository.deleteById(id);
        }
    }

    /**
     * 递归 封装 子菜单
     *
     * @param menuResultVo
     */
    private void fillChildrenMenu(MenuResultVo menuResultVo, Integer type) {
        Map<String, Object> meta = getMeta(menuResultVo);
        menuResultVo.setMeta(meta);
        List<MenuEntity> childrenMenuList = getChildrenMenu(menuResultVo.getId(), type);
        if (!CollectionUtils.isEmpty(childrenMenuList)) {
            menuResultVo.setChildren(childrenMenuList.stream().map(childrenMenu -> {
                MenuResultVo childrenMenuResultVo = new MenuResultVo();
                BeanUtils.copyProperties(childrenMenu, childrenMenuResultVo);
                childrenMenuResultVo.setName(childrenMenuResultVo.getLabel());
                fillChildrenMenu(childrenMenuResultVo, type);
                return childrenMenuResultVo;
            }).collect(Collectors.toList()));
        }
    }

    private List<MenuEntity> getChildrenMenu(Long parentId, Integer type) {
        if (type == null) {
            return menuRepository.findByParentIdOrderBySortOrderAsc(parentId);
        } else {
            return menuRepository.findByParentIdAndTypeIsNotOrderBySortOrderAsc(parentId, type);
        }

    }

    private Map<String, Object> getMeta(MenuResultVo menuResultVo) {
        Map<String, Object> meta = Maps.newHashMap();
        meta.put("title", menuResultVo.getLabel());

        //
        List<String> roleNameList = getMenuRoleNameList(menuResultVo.getId());
        log.info("【roleNameList : {}】", roleNameList);
        meta.put("roles", roleNameList);
        meta.put("icon", menuResultVo.getIcon());
        return meta;
    }

    private List<String> getMenuRoleNameList(Long id) {

        QRoleMenuEntity qRoleMenuEntity = QRoleMenuEntity.roleMenuEntity;
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;

        return Optional.ofNullable(queryFactory.select(qRoleEntity.roleName).distinct()
                .from(qRoleMenuEntity)
                .leftJoin(qRoleEntity).on(qRoleMenuEntity.roleId.eq(qRoleEntity.id))
                .where(qRoleMenuEntity.menuId.eq(id))
                .fetch())
                .orElse(new ArrayList<>());
    }
}