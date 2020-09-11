package org.johnny.blogscommon.vo.resultvo.system;

import lombok.Data;
import org.johnny.blogscommon.vo.common.BaseVo;

import java.util.List;

/**
 * 用户ResultVo
 *
 * @author johnny
 * @create 2020-07-13 下午5:00
 **/
@Data
public class UserResultVo extends BaseVo {

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


    private List<String> roles;

    private List<Long> roleIds;
    private String introduction;

    private String avatar;

    private String name;

}