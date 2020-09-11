package org.johnny.blogsserver.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.johnny.blogscommon.service.system.RoleMenuService;
import org.johnny.blogscommon.utils.PageUtil;
import org.johnny.blogscommon.utils.ResultVoUtil;
import org.johnny.blogscommon.vo.common.PageVo;
import org.johnny.blogscommon.vo.common.ResultVo;
import org.johnny.blogscommon.vo.requestvo.system.RoleMenuReqVo;
import org.johnny.blogscommon.vo.resultvo.system.RoleResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 *
 * @author johnny
 * @create 2020-07-13 下午12:51
 **/
@RestController
@RequestMapping("/roleMenu")
@Slf4j
public class RoleController {


    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 查询 根据 角色Id 查询 该角色关联的menuIds
     *
     * @param roleId
     * @return
     */
    @GetMapping("/findRoleMenuIds")
    public ResultVo findRoleMenuIds(@RequestParam("roleId") Long roleId) {
        List<Long> menuIds = roleMenuService.findMenuIdsByRoleId(roleId);
        return ResultVoUtil.success(menuIds);
    }

    @PostMapping("/roleMenuSubmit")
    public ResultVo roleMenuSubmit(@RequestBody RoleMenuReqVo roleMenuReqVo) {
        log.info("【roleMenuSubmit : {}】", roleMenuReqVo);

        roleMenuService.roleMenuSubmit(roleMenuReqVo);
        return ResultVoUtil.success();
    }

    @GetMapping("/findRole")
    public ResultVo<Page<RoleResultVo>> findRolePage(PageVo pageVo) {
        pageVo.setPageNumber(pageVo.getPageNumber() - 1);
        Page<RoleResultVo> page = roleMenuService.findRolePage(PageUtil.initPage(pageVo));
        return ResultVoUtil.success(page);
    }

}