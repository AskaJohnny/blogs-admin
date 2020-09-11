package org.johnny.blogscommon.form;

import lombok.Data;
import org.johnny.blogscommon.vo.common.PageVo;

import java.util.List;

/**
 * @author johnny
 * @create 2019-12-01 下午12:46
 **/
@Data
public class BlogInfoForm extends PageVo {

    private List<Long> ids;

}