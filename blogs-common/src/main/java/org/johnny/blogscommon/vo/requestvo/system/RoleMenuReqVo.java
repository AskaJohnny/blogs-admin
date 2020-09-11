package org.johnny.blogscommon.vo.requestvo.system;

import lombok.Data;

import java.util.List;

/**
 * 角色 和 菜单的 Req Vo
 *
 * @author johnny
 * @create 2020-07-13 下午2:37
 **/
@Data
public class RoleMenuReqVo {

    /**
     * 角色 分配的 菜单Id list
     */
    private List<Long> menuIds;

    /**
     * 角色 Id
     */
    private Long roleId;

}