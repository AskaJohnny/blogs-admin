package org.johnny.blogscommon.vo.blog;

import lombok.Data;

import java.util.List;

/**
 * @author johnny
 * @create 2019-12-28 下午3:16
 **/
@Data
public class ArchiveBlogVo {


    private String archiveDate;

    private List<BlogInfoVo> blogInfoVoList;
}