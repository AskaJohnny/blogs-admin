package org.johnny.blogscommon.entity.system;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单的 Entity
 *
 * @author johnny
 * @create 2020-07-10 下午2:02
 **/
@Data
@Entity
@Table(name = "blog_menu")
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String label;

    private Integer level;

    /**
     * 菜单类型 页面菜单/操作按钮
     */
    private Integer type;

    @Column(nullable = false)
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

    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    private Boolean enable;

    private Integer status;

    private String buttonRole;

    private String buttonRolePrefix;

    @Transient
    private List<MenuEntity> children;

    @Transient
    private List<String> roles;


    @Transient
    private Map<String, Object> meta = new HashMap();

}