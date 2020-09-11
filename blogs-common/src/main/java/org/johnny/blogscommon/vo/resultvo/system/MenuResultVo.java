package org.johnny.blogscommon.vo.resultvo.system;

import com.google.common.collect.Maps;
import lombok.Data;
import org.johnny.blogscommon.entity.system.MenuEntity;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author johnny
 * @create 2020-07-11 下午4:24
 **/
@Data
public class MenuResultVo {

    private Long id;

    private String label;

    private String name;

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

    private Integer status;

    private String buttonRole;

    private String buttonRolePrefix;

    private List<MenuResultVo> children;

    private List<String> roles;

    private Map<String, Object> meta = Maps.newHashMap();
}