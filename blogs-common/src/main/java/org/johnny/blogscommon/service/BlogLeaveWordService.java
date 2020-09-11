package org.johnny.blogscommon.service;

import org.johnny.blogscommon.vo.leaveword.BlogLeaveWordVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author johnny
 * @create 2019-12-19 下午5:10
 **/
public interface BlogLeaveWordService {
    Page<BlogLeaveWordVo> findPage(Pageable pageable, Long blogId);

    void save(BlogLeaveWordVo blogLeaveWordVo);
}