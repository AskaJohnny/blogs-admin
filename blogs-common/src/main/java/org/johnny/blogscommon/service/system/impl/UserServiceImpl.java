package org.johnny.blogscommon.service.system.impl;

import com.querydsl.core.Query;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.johnny.blogscommon.converter.RoleConverter;
import org.johnny.blogscommon.converter.UserConverter;
import org.johnny.blogscommon.entity.system.*;
import org.johnny.blogscommon.entity.user.QUserEntity;
import org.johnny.blogscommon.entity.user.QUserRoleEntity;
import org.johnny.blogscommon.entity.user.UserEntity;
import org.johnny.blogscommon.entity.user.UserRoleEntity;
import org.johnny.blogscommon.repository.system.RoleMenuRepository;
import org.johnny.blogscommon.repository.user.UserRepository;
import org.johnny.blogscommon.repository.user.UserRoleRepository;
import org.johnny.blogscommon.service.system.UserService;
import org.johnny.blogscommon.vo.common.BaseVo;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.common.QueryConditionVo;
import org.johnny.blogscommon.vo.requestvo.system.UserReqVo;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.johnny.blogscommon.vo.resultvo.system.UserResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author johnny
 * @create 2020-07-13 下午4:57
 **/
@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;


    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<UserResultVo> listByCondition(UserReqVo userReqVo, Pageable pageable) {

        List<UserResultVo> userResultVoList = new ArrayList<>();
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QUserRoleEntity qUserRoleEntity = QUserRoleEntity.userRoleEntity;

        queryFactory.select(qUserEntity)
                .from(qUserEntity)
                .leftJoin(qUserRoleEntity).on(qUserEntity.id.eq(qUserRoleEntity.userId));

        JPAQuery<UserEntity> jpaQuery = queryFactory.selectFrom(qUserEntity)
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize());

        fillCondition(jpaQuery, userReqVo);

        QueryResults<UserEntity> queryResults = jpaQuery.fetchResults();
        queryResults.getResults().forEach(userEntity -> {
            UserResultVo userResultVo = UserConverter.INSTANCE.domain2vo(userEntity);
            List<Long> roleIds = queryFactory.select(qUserRoleEntity)
                    .from(qUserRoleEntity).where(qUserRoleEntity.userId.eq(userEntity.getId()))
                    .fetch().stream().map(UserRoleEntity::getRoleId)
                    .collect(Collectors.toList());
            userResultVo.setRoleIds(roleIds);
            userResultVoList.add(userResultVo);
        });
        return new PageImpl<>(userResultVoList, pageable, queryResults.getTotal());
    }

    @Override
    public void submitUser(UserReqVo userReqVo) {
        Optional<Long> optionalId = Optional.ofNullable(userReqVo.getId());
        if (optionalId.isPresent()) {
            optionalId.flatMap(userId -> userRepository.findById(userId))
                    .ifPresent(userEntity -> {
                                userRepository.save(UserConverter.INSTANCE.vo2domain(userReqVo));
                                log.info("rolesId: {}", userReqVo.getRoleIds());
                                handleUserRole(userReqVo.getRoleIds(), userEntity.getId());
                            }
                    );
        } else {
            UserEntity userEntity = UserConverter.INSTANCE.vo2domain(userReqVo);
            userRepository.save(userEntity);
            handleUserRole(userReqVo.getRoleIds(), userEntity.getId());
        }
    }

    @Override
    public UserResultVo findUserInfo(Long id) {
        UserResultVo userResultVo = new UserResultVo();
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity != null) {
            List<String> roleNameList = new ArrayList<>();
            userResultVo = UserConverter.INSTANCE.domain2vo(userEntity);

            List<RoleEntity> roleList = getUserRoleNameList(id);
            if (!CollectionUtils.isEmpty(roleList)) {
                roleNameList = new ArrayList<>(roleList.stream().filter(roleEntity -> roleEntity.getRoleName() != null)
                        .map(RoleEntity::getRoleName).collect(Collectors.toList()));
                List<Long> roleIdList = roleList.stream().map(RoleEntity::getId).collect(Collectors.toList());

                //处理 button菜单 当做role
                List<String> menuButtonRole = getMenuRole(roleIdList);
                roleNameList.addAll(menuButtonRole);
                userResultVo.setRoles(roleNameList);
            } else {
                userResultVo.setRoles(Arrays.asList("error"));
            }
        }
        return userResultVo;
    }

    /**
     * 根据 用户名获取 用户信息 给SpringSecurity
     *
     * @param userName
     * @return
     */
    @Override
    public UserResultVo findByUserName(String userName) {
        UserResultVo userResultVo = new UserResultVo();
        Optional.ofNullable(userRepository.findByUsername(userName))
                .ifPresent(userEntity -> {
                    BeanUtils.copyProperties(userEntity, userResultVo);
                    List<RoleEntity> roleList = getUserRoleNameList(userResultVo.getId());
                    List<String> roleNameList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(roleList)) {
                        roleNameList = new ArrayList<>(roleList.stream().filter(roleEntity -> roleEntity.getRoleName() != null)
                                .map(RoleEntity::getRoleName).collect(Collectors.toList()));
                        List<Long> roleIdList = roleList.stream().map(RoleEntity::getId).collect(Collectors.toList());
                        //处理 button菜单 当做role
                        List<String> menuButtonRole = getMenuRole(roleIdList);
                        roleNameList.addAll(menuButtonRole);
                        userResultVo.setRoles(roleNameList);
                    } else {
                        userResultVo.setRoles(Arrays.asList("error"));
                    }
                });
        if (userResultVo.getId() != null) {
            return userResultVo;
        }
        return null;
    }

    private List<String> getMenuRole(List<Long> roleIdList) {
        QRoleMenuEntity qRoleMenuEntity = QRoleMenuEntity.roleMenuEntity;
        QMenuEntity qMenuEntity = QMenuEntity.menuEntity;

        List<String> menuRoleList = Optional.ofNullable(queryFactory.select(qMenuEntity)
                .from(qRoleMenuEntity)
                .leftJoin(qMenuEntity).on(qRoleMenuEntity.menuId.eq(qMenuEntity.id))
                .where(qRoleMenuEntity.roleId.in(roleIdList).and(qMenuEntity.type.eq(1)))
                .fetch())
                .map(menuList -> menuList.stream().map(menu -> {
                    return menu.getButtonRolePrefix() + menu.getButtonRole();
                }).collect(Collectors.toList())).orElse(new ArrayList<>());

        return menuRoleList;
    }


    private List<RoleEntity> getUserRoleNameList(Long id) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;
        QUserRoleEntity qUserRoleEntity = QUserRoleEntity.userRoleEntity;

        List<RoleEntity> roleEntities = Optional.ofNullable(queryFactory.select(qRoleEntity).distinct()
                .from(qUserEntity)
                .leftJoin(qUserRoleEntity).on(qUserRoleEntity.userId.eq(qUserEntity.id))
                .leftJoin(qRoleEntity).on(qRoleEntity.id.eq(qUserRoleEntity.roleId))
                .where(qUserEntity.id.eq(id))
                .fetch()).orElse(null);

        roleEntities.removeAll(Collections.singleton(null));
        System.out.println(roleEntities);
        return roleEntities;
    }

    private void handleUserRole(List<Long> roleIds, Long userId) {
        userRoleRepository.deleteByUserId(userId);
        roleIds.forEach(roleId -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userId);
            userRoleRepository.save(userRoleEntity);
        });
    }

    /**
     * 封装查询条件
     *
     * @param jpaQuery
     */
    @Override
    public void fillCondition(JPAQuery<UserEntity> jpaQuery, QueryConditionVo queryConditionVo) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        UserReqVo userReqVo = (UserReqVo) queryConditionVo;
        if (StringUtils.isNotEmpty(userReqVo.getUsername())) {
            jpaQuery.where(qUserEntity.username.like("%" + userReqVo.getUsername() + "%"));
        }
    }
}