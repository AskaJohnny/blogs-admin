package org.johnny.blogscommon.vo.resume;

import lombok.Data;

/**
 * @author johnny
 * @create 2019-12-07 下午8:42
 **/
@Data
public class ResumeVo {

    private Long id;

    /**
     * 简历内容
     */
    private String resumeContent;
}