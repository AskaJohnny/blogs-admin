package org.johnny.blogscommon.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色 菜单 Entity
 *
 * @author johnny
 * @create 2020-07-13 下午1:44
 **/

@Data
@Entity
@Table(name = "blog_role_menu")
@AllArgsConstructor
public class RoleMenuEntity extends BaseEntity {

    private Long menuId;

    private Long roleId;

    public RoleMenuEntity() {
    }


}