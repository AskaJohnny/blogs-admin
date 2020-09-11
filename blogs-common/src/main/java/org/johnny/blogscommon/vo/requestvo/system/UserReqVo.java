package org.johnny.blogscommon.vo.requestvo.system;

import lombok.Data;
import org.johnny.blogscommon.vo.common.BaseVo;

import java.util.List;

/**
 * 用户查询 Vo
 *
 * @author johnny
 * @create 2020-07-13 下午4:53
 **/
@Data
public class UserReqVo extends BaseVo {


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


    private List<Long> roleIds;

}