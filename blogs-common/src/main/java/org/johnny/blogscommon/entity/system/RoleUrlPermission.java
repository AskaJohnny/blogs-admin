package org.johnny.blogscommon.entity.system;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 请求 url 和 role 的关系表
 *
 * @author johnny
 * @create 2020-08-19 上午10:16
 **/
@Data
@Entity
@Table(name = "role_url_permission")
public class RoleUrlPermission extends BaseEntity {

    private Long roleId;

    private Long urlId;


}