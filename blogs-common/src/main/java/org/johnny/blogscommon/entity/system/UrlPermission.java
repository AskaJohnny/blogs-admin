package org.johnny.blogscommon.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 请求 Url 和 角色的 关系
 *
 * @author johnny
 * @create 2020-08-19 上午10:08
 **/
@Data
@Entity
@Table(name = "url_req_permission")
@AllArgsConstructor
public class UrlPermission  extends BaseEntity {

    /**
     * 请求路径
     */
    private String urlRequest;

    /**
     * 对这个请求的描述
     */
    private String shortDesc;



}