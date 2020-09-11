package org.johnny.blogscommon.vo.resultvo.system;

import lombok.Data;
import org.johnny.blogscommon.vo.common.BaseVo;

/**
 * Role Result vo
 *
 * @author johnny
 * @create 2020-07-13 下午3:09
 **/
@Data
public class RoleResultVo extends BaseVo {

    private String roleName;

    private String roleDesc;
}