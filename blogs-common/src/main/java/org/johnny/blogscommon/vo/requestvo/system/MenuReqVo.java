package org.johnny.blogscommon.vo.requestvo.system;

import lombok.Data;
import org.johnny.blogscommon.vo.common.ReqBaseVo;

import java.math.BigDecimal;

/**
 * 菜单Vo
 *
 * @author johnny
 * @create 2020-07-11 下午4:12
 **/
@Data
public class MenuReqVo extends ReqBaseVo {

    private Long id;
    private String label;

    private Integer level;

    /**
     * 菜单类型 页面菜单/操作按钮
     */
    private Integer type;

    private String path;

    /**
     * 容器组件 使用Layout
     */
    private String component;

    /**
     * 图标 icon
     */
    private String icon;

    private Long parentId;

    private String description;

    private BigDecimal sortOrder;

    private Boolean enable;

    private String buttonRole;

    private String buttonRolePrefix;



}