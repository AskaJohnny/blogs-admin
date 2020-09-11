package org.johnny.blogscommon.service.system.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.johnny.blogscommon.converter.RoleConverter;
import org.johnny.blogscommon.entity.system.QRoleEntity;
import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.entity.system.RoleMenuEntity;
import org.johnny.blogscommon.repository.system.RoleMenuRepository;
import org.johnny.blogscommon.service.system.RoleMenuService;
import org.johnny.blogscommon.utils.DateUtils;
import org.johnny.blogscommon.vo.requestvo.system.RoleMenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.relation.RoleResult;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单 Service Impl
 *
 * @author johnny
 * @create 2020-07-13 下午1:47
 **/
@Service
@Transactional(rollbackOn = Exception.class)
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuRepository roleMenuRepository;
    @Autowired
    private JPAQueryFactory queryFactory;


    /**
     * 根据 角色Id 查询关联的 菜单Id
     *
     * @param roleId : 角色Id
     * @return : List 菜单Id
     */
    @Override
    public List<Long> findMenuIdsByRoleId(Long roleId) {
        List<Long> roleMenuIds = new ArrayList<>();
        if (roleId != null) {
            roleMenuRepository.findByRoleId(roleId).
                    forEach(roleMenuEntity -> roleMenuIds.add(roleMenuEntity.getMenuId()));
        }
        return roleMenuIds;
    }

    /**
     * 角色 和 菜单的 关系 Submit
     *
     * @param roleMenuReqVo: 角色菜单Req
     */
    @Override
    public void roleMenuSubmit(RoleMenuReqVo roleMenuReqVo) {
        if (roleMenuReqVo.getRoleId() != null) {
            List<RoleMenuEntity> newRoleMenuEntityList = new ArrayList<>();
            //删除 原始角色和菜单的关系
            roleMenuRepository.deleteByRoleId(roleMenuReqVo.getRoleId());
            roleMenuReqVo.getMenuIds().forEach(menuId -> {
                RoleMenuEntity roleMenuEntity = new RoleMenuEntity(menuId, roleMenuReqVo.getRoleId());
                newRoleMenuEntityList.add(roleMenuEntity);
            });
            //保存新的 角色和 菜单的关系
            if (!CollectionUtils.isEmpty(newRoleMenuEntityList)) {
                roleMenuRepository.saveAll(newRoleMenuEntityList);
            }
        }
    }

    @Override
    public Page<RoleResultVo> findRolePage(Pageable pageable) {

        List<RoleResultVo> roleResultVos = new ArrayList<>();
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;

        JPAQuery<RoleEntity> jpaQuery = queryFactory.selectFrom(qRoleEntity)
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize());

        QueryResults<RoleEntity> queryResults = jpaQuery.fetchResults();
        queryResults.getResults().forEach(roleEntity -> {
            RoleResultVo roleResultVo = RoleConverter.INSTANCE.domain2vo(roleEntity);
            roleResultVos.add(roleResultVo);
        });
        return new PageImpl<>(roleResultVos, pageable, queryResults.getTotal());
    }
}