package org.johnny.blogscommon.service.system;

import org.johnny.blogscommon.vo.requestvo.system.RoleMenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色 菜单 service
 * @author johnny
 * @create 2020-07-13 下午1:46
 **/
public interface RoleMenuService {

    List<Long> findMenuIdsByRoleId(Long roleId);

    void roleMenuSubmit(RoleMenuReqVo roleMenuReqVo);

    Page<RoleResultVo> findRolePage(Pageable initPage);
}