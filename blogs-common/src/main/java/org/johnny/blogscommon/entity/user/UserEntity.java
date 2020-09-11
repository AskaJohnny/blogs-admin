package org.johnny.blogscommon.entity.user;

import lombok.Data;
import org.johnny.blogscommon.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户 实体
 *
 * @author johnny
 * @create 2020-07-10 下午4:05
 **/
@Data
@Entity
@Table(name = "blog_user")
public class UserEntity extends BaseEntity {


    private String username;

    private String password;

    /**
     * 性别
     */
    private Integer gender;

    private String phone;

    private String email;

    private String headImage;

    private Boolean enable;


}