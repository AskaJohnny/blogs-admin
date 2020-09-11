package org.johnny.blogsauth.voter;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.entity.system.QRoleUrlPermission;
import org.johnny.blogscommon.entity.system.QUrlPermission;
import org.johnny.blogscommon.entity.system.RoleEntity;
import org.johnny.blogscommon.repository.system.RoleRepository;
import org.johnny.blogscommon.repository.system.UrlPermissionRepository;
import org.johnny.blogscommon.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色的 元数据
 *
 * @author johnny
 * @create 2020-07-22 下午9:15
 **/
@Slf4j
@Component
public class RoleSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JPAQueryFactory queryFactory;


    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/user/listByCondition**", "/user/listByCondition?username=&phone=&pageNumber=0&pageSize=10"));
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //根据 请求获取 需要的权限
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        log.info("【请求 url : {}】", url);
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //
        QUrlPermission qUrlPermission = QUrlPermission.urlPermission;
        QRoleUrlPermission qRoleUrlPermission = QRoleUrlPermission.roleUrlPermission;

        Optional.ofNullable(queryFactory.select(qRoleUrlPermission.roleId)
                .from(qRoleUrlPermission)
                .leftJoin(qUrlPermission)
                .on(qUrlPermission.id.eq(qRoleUrlPermission.urlId))
                .where(qUrlPermission.urlRequest.eq(url))
                .fetch()).flatMap(roleList -> Optional.ofNullable(roleRepository.findByIdIn(roleList))).ifPresent(roleEntities -> {
            List<String> roleNameList = roleEntities.stream().map(RoleEntity::getRoleName).collect(Collectors.toList());
            roleNameList.forEach(roleName -> {
                configAttributes.add(new SecurityConfig(roleName));
            });
        });
        return configAttributes;
//        Map<String, List<String>> urlRoleMap = new HashMap();
//        urlRoleMap.put("/menu/**", Arrays.asList("admin", "editor1"));
//        urlRoleMap.put("/user/listByCondition**", Arrays.asList("admin1", "editor1"));
//
//        for (Map.Entry<String, List<String>> entry : urlRoleMap.entrySet()) {
//            if (antPathMatcher.match(entry.getKey(), url)) {
//                String[] array = entry.getValue().toArray(new String[0]);
//                return SecurityConfig.createList(array);
//            }
//        }
        //return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}