package org.johnny.blogscommon.entity.user;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色 关系表
 *
 * @author johnny
 * @create 2020-07-14 上午9:11
 **/
@Entity
@Table(name = "blog_user_role")
@Data
public class UserRoleEntity extends BaseEntity {

    private Long userId;


    private Long roleId;

}