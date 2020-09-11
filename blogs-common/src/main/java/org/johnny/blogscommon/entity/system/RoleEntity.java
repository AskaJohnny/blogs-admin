package org.johnny.blogscommon.entity.system;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色
 *
 * @author johnny
 * @create 2020-07-13 下午1:42
 **/
@Data
@Entity
@Table(name = "blog_role")
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleDesc;

}